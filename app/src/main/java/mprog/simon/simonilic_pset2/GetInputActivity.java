package mprog.simon.simonilic_pset2;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import java.io.InputStream;

public class GetInputActivity extends AppCompatActivity {

    int[] txt_files = {R.raw.madlib0_simple, R.raw.madlib1_tarzan,
    R.raw.madlib2_university, R.raw.madlib3_clothes, R.raw.madlib4_dance};

    Story story;
    int story_id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_get_input);

        // get intetent extra's from parent
        Intent parent_intent = getIntent();
        story_id = parent_intent.getIntExtra("story_id", 0);

        if (savedInstanceState == null) {
            // open file in inputstream and create story object
            InputStream targetStream = getApplicationContext().getResources().openRawResource(txt_files[story_id]);
            story = new Story(targetStream);
        }
        else
        {
            story = (Story) savedInstanceState.getSerializable("story");
        }


        updateView();
    }

    public void handleInputText(View view) {
        EditText editText = (EditText) findViewById(R.id.inputText);
        String word = editText.getText().toString();

        if (word.isEmpty()) {
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Please give me a word!", Toast.LENGTH_SHORT);
            toast.show();

            return;
        }

        story.fillInPlaceholder(word);

        if (story.isFilledIn()) {
            // go to display story view
            Intent intent = new Intent(this, DisplayStoryActivity.class);
            intent.putExtra("story", story.toString());
            intent.putExtra("story_id", story_id);
            startActivity(intent);
            finish();
        }
        else {
            // show a confirmation toast
            Toast toast = Toast.makeText(getApplicationContext(),
                    "Great, keep going!", Toast.LENGTH_SHORT);
            toast.show();

            updateView();
        }
    }

    public void updateView() {
        TextView word_countView = (TextView) findViewById(R.id.word_countView);
        word_countView.setText(getString(R.string.word_count, story.getPlaceholderRemainingCount()));

        // set hint in edit text and text view
        String hint = story.getNextPlaceholder();

        EditText editText = (EditText) findViewById(R.id.inputText);
        editText.setHint(getString(R.string.inputText_hint, hint));

        TextView extra_hint = (TextView) findViewById(R.id.extra_hint);
        extra_hint.setText(getString(R.string.extra_hint, hint));

        // clear text field
        editText.setText("");
    }

    // Save state when screen rotates etc.
    @Override
    public void onSaveInstanceState(Bundle savedInstanceState) {

        // Save UI state changes to the savedInstanceState.
        // This bundle will be passed to onCreate if the process is
        // killed and restarted.

        savedInstanceState.putSerializable("story", story);
        super.onSaveInstanceState(savedInstanceState);
    }
}
