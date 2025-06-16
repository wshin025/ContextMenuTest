package kr.ac.kopo.contextmenutest;

import android.graphics.Color;
import android.os.Bundle;
import android.view.ContextMenu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class MainActivity extends AppCompatActivity {
    Button btnBg, btnButton;
    LinearLayout linear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });
        btnBg = findViewById(R.id.btn_bg);
        btnButton = findViewById(R.id.btn_button);
        linear = findViewById(R.id.main);
        registerForContextMenu(btnBg);
        registerForContextMenu(btnButton);
    }

    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        super.onCreateContextMenu(menu, v, menuInfo);

        MenuInflater menuInflater = getMenuInflater();
        if (v == btnBg) {
            menu.setHeaderTitle("배경색 변경");
            menuInflater.inflate(R.menu.menu_bg, menu);
        }

        if (v == btnButton) {
            menu.setHeaderTitle("버튼 변경");
            menuInflater.inflate(R.menu.menu_button, menu);
        }
    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        super.onContextItemSelected(item);
        if (item.getItemId() == R.id.item_bg_red) {
            linear.setBackgroundColor(Color.RED);
            return true;
        } else if (item.getItemId() == R.id.item_bg_blue) {
            linear.setBackgroundColor(Color.BLUE);
            return true;
        } else if (item.getItemId() == R.id.item_bg_green) {
            linear.setBackgroundColor(Color.GREEN);
            return true;
        } else if (item.getItemId() == R.id.item_btn_rotation) {
            btnButton.setRotation(btnButton.getRotation() + 45);
            return true;
        } else if (item.getItemId() == R.id.item_btn_zoomin) {
            btnButton.setScaleX(2);
            return true;
        } else if (item.getItemId() == R.id.item_btn_basic) {
            btnButton.setRotation(0);
            btnButton.setScaleX(1);
            return true;
        }
        return false;
    }
}