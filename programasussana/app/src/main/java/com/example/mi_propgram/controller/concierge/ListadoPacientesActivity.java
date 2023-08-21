package com.example.mi_propgram.controller.concierge;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.ImageView;
import android.widget.PopupMenu;

import com.example.mi_propgram.R;
import com.example.mi_propgram.controller.adapter.UsuariosCitaPagerAdapter;
import com.example.mi_propgram.controller.admin.AddUserQuoteActivity;
import com.example.mi_propgram.controller.admin.MenuActivity;
import com.example.mi_propgram.controller.concierge.fragments.AllUsersFragment;
import com.example.mi_propgram.controller.concierge.fragments.WitnessUsersFragment;
import com.example.mi_propgram.controller.concierge.fragments.PendingUsersFragment;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class ListadoPacientesActivity extends AppCompatActivity {
    private ViewPager2 viewPager;
    private TabLayout tabLayout;
    private ImageView idBtnBack, idBtnOptions;
    private String isAdmin;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listado_pacientes);

        setupInitializeUi();
        setupGetParams();
        setupInitializeViewPager();
        setupClickListeners();

    }

    private void setupGetParams() {
        Intent intent = getIntent();
        if (intent != null) {
            isAdmin = intent.getStringExtra("isAdmin");
        }
    }

    private void setupClickListeners() {
        idBtnBack.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
        idBtnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu popupMenu = new PopupMenu(ListadoPacientesActivity.this, v);
                popupMenu.inflate(R.menu.menu_options_top);

                popupMenu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @SuppressLint("NonConstantResourceId")
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()) {
                            case R.id.id_option_delete: {

                            }
                            return true;
                            case R.id.id_option_add:
                                Intent intent = new Intent(v.getContext(), AddUserQuoteActivity.class);
                                startActivity(intent);
                                return true;
                        }
                        return false;
                    }
                });

                popupMenu.show();
            }
        });
    }

    private void setupInitializeViewPager() {
        UsuariosCitaPagerAdapter adapter = new UsuariosCitaPagerAdapter(this);
        adapter.addFragment(new AllUsersFragment());
        adapter.addFragment(new PendingUsersFragment());
        adapter.addFragment(new WitnessUsersFragment());
        viewPager.setAdapter(adapter);

        TabLayoutMediator tabLayoutMediator = new TabLayoutMediator(tabLayout, viewPager,
                (tab, position) -> {
                    switch (position) {
                        case 0:
                            tab.setText("Usuarios");
                            break;
                        case 1:
                            tab.setText("Pendientes");
                            break;
                        case 2:
                            tab.setText("Asistidos");
                            break;
                    }
                });
        tabLayoutMediator.attach();
    }

    private void setupInitializeUi() {
        idBtnBack = findViewById(R.id.idBtnBack);
        idBtnOptions = findViewById(R.id.idBtnOptions);
        viewPager = findViewById(R.id.viewPager);
        tabLayout = findViewById(R.id.tabLayout);
    }
}