package com.example.mi_propgram.controller.admin;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.GenericAdapter;
import com.example.mi_propgram.controller.auth.RegisterUserActivity;
import com.example.mi_propgram.controller.consultas.ListarUsuariosSistema;
import com.example.mi_propgram.controller.interfaces.CallbackInterface;
import com.example.mi_propgram.holders.UserSystemHolder;
import com.example.mi_propgram.models.UsuarioModel;

import java.util.List;

public class ListUsersSystemActivity extends AppCompatActivity {

    private LinearLayout addNewUserSystem;
    private RecyclerView recyclerView;
    private ProgressBar idProgressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_users_system);
        initUI();
    }

    private void initUI() {
        initComponents();
        setupClicksListeners();
        initRcv();
        setupGetData();
    }

    private void setupGetData() {
        ListarUsuariosSistema.getUsersSystem(new CallbackInterface<List<UsuarioModel>>() {
            @Override
            public void onSuccess(List<UsuarioModel> result) {
                GenericAdapter<UsuarioModel, UserSystemHolder> usersSystemAdapter = new GenericAdapter<>(
                        result,
                        R.layout.item_user_system,
                        new GenericAdapter.ViewCreator<UserSystemHolder>() {
                            @Override
                            public UserSystemHolder create(View view) {
                                return new UserSystemHolder(view);
                            }
                        },
                        new GenericAdapter.ItemClickListener<UserSystemHolder, UsuarioModel>() {
                            @Override
                            public void onClick(UserSystemHolder viewHolder, UsuarioModel item) {
                                viewHolder.render(item, new UserSystemHolder.OnItemClickListener() {
                                    @Override
                                    public void onClick(UsuarioModel dataFileUsers) {
                                        Log.e("", "Click");
                                    }
                                });
                            }
                        }
                );
                idProgressBar.setVisibility(View.GONE);
                recyclerView.setAdapter(usersSystemAdapter);
            }

            @Override
            public void onError(String errorMessage) {
                //
            }
        });
    }

    private void initRcv() {
        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(this);
        recyclerView.setLayoutManager(layoutManager);
    }

    private void setupClicksListeners() {
        addNewUserSystem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(v.getContext(), RegisterUserActivity.class);
                startActivity(intent);
            }
        });
    }

    private void initComponents() {
        addNewUserSystem = findViewById(R.id.addNewUserSystem);
        recyclerView = findViewById(R.id.rcvListUserSystem);
        idProgressBar = findViewById(R.id.progressBar);
    }
}