package mprog.simon.simonilic_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class StartActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
    }

    /* handle begin button click */
    public void forward(View view) {
        Intent intent = new Intent(this, GetInputActivity.class);
        startActivity(intent);
    }
}
