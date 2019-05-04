package com.example.simpleforumchatapplication;

import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    FirebaseDatabase database;
    DatabaseReference myRef;
    private String TAG = "FireBase-Log";
    GoogleSignInClient mGoogleSignInClient;
    int RC_SIGN_IN = 1;
    private static String UserId="";
    private static String Email="";
    DatabaseReference userRef;
    DatabaseReference messageRef;
    ImageView ivSend;
    EditText edtMessage;
    ListView listView;
    MessageAdapter adapter;
    List<MessageModel> modelList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Google Sign-in//
        GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken("400121687859-c2dkv7ikb52fd17tpri4pcv58p62bi76.apps.googleusercontent.com")
                .build();
        mGoogleSignInClient = GoogleSignIn.getClient(this, gso);
        signIn();
        ivSend = findViewById(R.id.btn_SendMessage);
        edtMessage = findViewById(R.id.editText);
        listView = findViewById(R.id.messages_view);
        modelList = new ArrayList<>();

    }

    @Override
    protected void onStart() {
        super.onStart();
        ivSend.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SendMessage();
            }
        });
    }

    private void SendMessage(){
        String mKey = messageRef.push().getKey();
        MessageModel model = new MessageModel();
        model.setUserName(Email);
        model.setText(edtMessage.getText().toString());
        model.setUserId(UserId);
        messageRef.child(mKey).setValue(model);
        edtMessage.setText("");
    }

    private void ReceiveMessage(){
        messageRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                if (adapter == null){
                    for (DataSnapshot ds : dataSnapshot.getChildren()){
                        MessageModel model = ds.getValue(MessageModel.class);
                        model.setTextId(ds.getKey());
                        if (model.getUserId().equals(UserId)){
                            model.setCurrentUser(true);
                        }
                        modelList.add(model);
                    }
                    if (modelList.size() > 0){
                        adapter = new MessageAdapter(MainActivity.this,R.layout.their_message_layout,R.layout.my_message_layout,modelList);
                        listView.setAdapter(adapter);
                    }
                }
                else{
                    for (DataSnapshot ds: dataSnapshot.getChildren()){
                        boolean isRepeat = false;
                        for (MessageModel item : modelList){
                            if (item.getTextId().equals(ds.getKey())){
                                isRepeat = true;
                                break;
                            }
                        }
                        if (isRepeat == false){
                            MessageModel model = ds.getValue(MessageModel.class);
                            model.setTextId(ds.getKey());
                            if (model.getUserId().equals(UserId)){
                                model.setCurrentUser(true);
                            }
                            adapter.AddItem(model);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }

    private void signIn() {
        Intent signInIntent = mGoogleSignInClient.getSignInIntent();
        startActivityForResult(signInIntent, RC_SIGN_IN);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        // Result returned from launching the Intent from GoogleSignInClient.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            // The Task returned from this call is always completed, no need to attach
            // a listener.
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w(TAG, "Google sign in failed", e);
                signIn();
                // ...
            }
        }
    }

    private void firebaseAuthWithGoogle(GoogleSignInAccount account){
        UserId = account.getId();
        Email = account.getDisplayName();
        database = FirebaseDatabase.getInstance();
        UserModel model = new UserModel();
        model.setEmail(Email);
        userRef = database.getReference("User");
        userRef.child(UserId).setValue(model);
        messageRef = database.getReference("Message");
        ReceiveMessage();
        // Signed in successfully, show authenticated UI.
    }

}
