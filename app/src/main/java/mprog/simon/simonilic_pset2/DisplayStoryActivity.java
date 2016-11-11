package mprog.simon.simonilic_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

public class DisplayStoryActivity extends AppCompatActivity {
    static final int N_STORIES = 5;

    int story_id;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_display_story);

        // get intetent extra's from parent
        Intent parent_intent = getIntent();
        String story_text = parent_intent.getStringExtra("story");
        story_id = parent_intent.getIntExtra("story_id", 0);

        if (story_text.isEmpty()) {
            story_text = "Sorry, something went wrong";
        }

        TextView storyView = (TextView) findViewById(R.id.story);
        storyView.setText(Html.fromHtml(story_text));
    }

    public void newStory(View view) {

        if (story_id + 1 < N_STORIES) {
            Intent intent = new Intent(this, GetInputActivity.class);
            intent.putExtra("story_id", story_id + 1);
            startActivity(intent);
            finish();
        }
        else
        {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Sorry, that was all...", Toast.LENGTH_LONG);
            toast.show();

            // go to home
            Intent intent = new Intent(this, StartActivity.class);
            startActivity(intent);
            finish();
        }
    }
}
