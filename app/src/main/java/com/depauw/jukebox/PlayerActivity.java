package com.depauw.jukebox;

import android.graphics.Color;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.RadioGroup;
import android.widget.SeekBar;

import androidx.appcompat.app.AppCompatActivity;
import com.depauw.jukebox.databinding.ActivityPlayerBinding;

public class PlayerActivity extends AppCompatActivity implements RadioGroup.OnCheckedChangeListener {

    private ActivityPlayerBinding binding;
    private MediaPlayer md;

    private static float AVERAGE_RATING_TRACK1 = 0.0f;
    private static float AVERAGE_RATING_TRACK2 = 0.0f;
    private static float AVERAGE_RATING_TRACK3 = 0.0f;

    private static int RED_VALUE = 0;
    private static int GREEN_VALUE = 0;
    private static int BLUE_VALUE = 0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player);
        binding = ActivityPlayerBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        int checkedButtonIndex = binding.radiogroupSongs.getCheckedRadioButtonId() - binding.radioSong1.getId();
        switch(checkedButtonIndex) {
            case 0:
                binding.imageviewAlbumCover.setImageResource(R.drawable.track1);
                md = MediaPlayer.create(this, R.raw.track1);
                md.start();
                break;
            case 1:
                binding.imageviewAlbumCover.setImageResource(R.drawable.track2);
                md = MediaPlayer.create(this, R.raw.track2);
                md.start();
                break;
            case 2:
                binding.imageviewAlbumCover.setImageResource(R.drawable.track3);
                md = MediaPlayer.create(this, R.raw.track3);
                md.start();
                break;
        }
        binding.seekbarRed.setOnSeekBarChangeListener(seekbar_red_changeListener);
        binding.seekbarGreen.setOnSeekBarChangeListener(seekbar_green_changeListener);
        binding.seekbarBlue.setOnSeekBarChangeListener(seekbar_blue_changeListener);
        binding.seekbarSongPosition.setOnSeekBarChangeListener(seekbar_song_position_changeListener);
        binding.radiogroupSongs.setOnCheckedChangeListener(this);
        binding.buttonCastVote.setOnClickListener(button_cast_vote_clickListener);
    }

    private  SeekBar.OnSeekBarChangeListener seekbar_red_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            binding.textviewRed.setText(String.valueOf(i));
            RED_VALUE = Integer.valueOf(binding.textviewRed.getText().toString());
            GREEN_VALUE = Integer.valueOf(binding.textviewGreen.getText().toString());
            BLUE_VALUE = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            binding.constraintlayout.setBackgroundColor(updatedColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekbar_green_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            binding.textviewGreen.setText(String.valueOf(i));
            RED_VALUE = Integer.valueOf(binding.textviewRed.getText().toString());
            GREEN_VALUE = Integer.valueOf(binding.textviewGreen.getText().toString());
            BLUE_VALUE = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            binding.constraintlayout.setBackgroundColor(updatedColor);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekbar_blue_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            binding.textviewBlue.setText(String.valueOf(i));
            RED_VALUE = Integer.valueOf(binding.textviewRed.getText().toString());
            GREEN_VALUE = Integer.valueOf(binding.textviewGreen.getText().toString());
            BLUE_VALUE = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(RED_VALUE, GREEN_VALUE, BLUE_VALUE);
            binding.constraintlayout.setBackgroundColor(updatedColor);

        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private SeekBar.OnSeekBarChangeListener seekbar_song_position_changeListener = new SeekBar.OnSeekBarChangeListener() {
        @Override
        public void onProgressChanged(SeekBar seekBar, int i, boolean b) {
            md.pause();
            double seekFrac = i/100.0;
            int newStatus = (int) (md.getDuration() * seekFrac);
            md.seekTo(newStatus);
            md.start();
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {

        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    @Override
    public void onCheckedChanged(RadioGroup radioGroup, int i) {
        int checkedButtonIndex = i - binding.radioSong1.getId();
        switch(checkedButtonIndex) {
            case 0:
                binding.seekbarSongPosition.setProgress(0);
                binding.imageviewAlbumCover.setImageResource(R.drawable.track1);
                md.stop();
                md = MediaPlayer.create(this, R.raw.track1);
                md.start();
                break;
            case 1:
                binding.seekbarSongPosition.setProgress(0);
                binding.imageviewAlbumCover.setImageResource(R.drawable.track2);
                md.stop();
                md = MediaPlayer.create(this, R.raw.track2);
                md.start();
                break;
            case 2:
                binding.seekbarSongPosition.setProgress(0);
                binding.imageviewAlbumCover.setImageResource(R.drawable.track3);
                md.stop();
                md = MediaPlayer.create(this, R.raw.track3);
                md.start();
                break;
        }
    }

    private View.OnClickListener button_cast_vote_clickListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            int newAverage=0, newNumVotes;
            switch(binding.radiogroupSongs.getCheckedRadioButtonId())
            {
                case R.id.radio_song1:
                    newNumVotes = Integer.valueOf(binding.textviewNumVotes1.getText().toString()) + 1;
                    if(newNumVotes>1)
                    {
//                        newAverage = ((binding.progressbarAverageRating1.getProgress() * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
//                        AVERAGE_RATING_TRACK1 = Math.round(((AVERAGE_RATING_TRACK1 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes);
                        AVERAGE_RATING_TRACK1 = ((AVERAGE_RATING_TRACK1 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;

                    }
                    else
                    {
//                        newAverage = binding.ratingbarVoterRating.getProgress();
//                        AVERAGE_RATING_TRACK1 = Math.round(binding.ratingbarVoterRating.getProgress());
                        AVERAGE_RATING_TRACK1 = binding.ratingbarVoterRating.getProgress();
                    }
                    binding.textviewNumVotes1.setText(String.valueOf(newNumVotes));
                    binding.progressbarAverageRating1.setProgress(Math.round(AVERAGE_RATING_TRACK1));
                    Log.d("vidit", String.valueOf(AVERAGE_RATING_TRACK1));
                    break;
                case R.id.radio_song2:
                    newNumVotes = Integer.valueOf(binding.textviewNumVotes2.getText().toString()) + 1;
                    if(newNumVotes>1)
                    {
//                        newAverage = ((binding.progressbarAverageRating2.getProgress() * Integer.valueOf(binding.textviewNumVotes2.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
//                        AVERAGE_RATING_TRACK2 = Math.round(((AVERAGE_RATING_TRACK2 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes);
                        AVERAGE_RATING_TRACK2 = ((AVERAGE_RATING_TRACK2 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
                    }
                    else
                    {
//                        newAverage = binding.ratingbarVoterRating.getProgress();
//                        AVERAGE_RATING_TRACK2 = Math.round(binding.ratingbarVoterRating.getProgress());
                        AVERAGE_RATING_TRACK2 = binding.ratingbarVoterRating.getProgress();
                    }
                    binding.textviewNumVotes2.setText(String.valueOf(newNumVotes));
//                    binding.progressbarAverageRating2.setProgress(newAverage);
                    binding.progressbarAverageRating2.setProgress(Math.round(AVERAGE_RATING_TRACK2));
                    Log.d("vidit", String.valueOf(AVERAGE_RATING_TRACK2));

                    break;
                case R.id.radio_song3:
                    newNumVotes = Integer.valueOf(binding.textviewNumVotes3.getText().toString()) + 1;
                    if(newNumVotes>1)
                    {
//                        newAverage = ((binding.progressbarAverageRating3.getProgress() * Integer.valueOf(binding.textviewNumVotes3.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
//                        AVERAGE_RATING_TRACK3 = Math.round(((AVERAGE_RATING_TRACK3 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes);
                        AVERAGE_RATING_TRACK3 = ((AVERAGE_RATING_TRACK3 * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;

                    }
                    else
                    {
//                        newAverage = binding.ratingbarVoterRating.getProgress();
//                        AVERAGE_RATING_TRACK3 = Math.round(binding.ratingbarVoterRating.getProgress());
                        AVERAGE_RATING_TRACK3 = binding.ratingbarVoterRating.getProgress();
                    }

                    binding.textviewNumVotes3.setText(String.valueOf(newNumVotes));
//                    binding.progressbarAverageRating3.setProgress(newAverage);
                    binding.progressbarAverageRating3.setProgress(Math.round(AVERAGE_RATING_TRACK3));
                    Log.d("vidit", String.valueOf(AVERAGE_RATING_TRACK3));
                    break;
            }
            binding.ratingbarVoterRating.setRating(0.0f);
        }
    };
}

