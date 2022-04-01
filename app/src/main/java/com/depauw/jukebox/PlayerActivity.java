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

    ActivityPlayerBinding binding;
    MediaPlayer md;

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
        binding.imageviewAlbumCover.setImageResource(R.drawable.track1);
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
            int red = Integer.valueOf(binding.textviewRed.getText().toString());
            int green = Integer.valueOf(binding.textviewGreen.getText().toString());
            int blue = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(red, green, blue);
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
            int red = Integer.valueOf(binding.textviewRed.getText().toString());
            int green = Integer.valueOf(binding.textviewGreen.getText().toString());
            int blue = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(red, green, blue);
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
            int red = Integer.valueOf(binding.textviewRed.getText().toString());
            int green = Integer.valueOf(binding.textviewGreen.getText().toString());
            int blue = Integer.valueOf(binding.textviewBlue.getText().toString());
            int updatedColor = Color.rgb(red, green, blue);
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
            double seekFrac = i/100.0;
            int newStatus = (int) (md.getDuration() * seekFrac);
            md.seekTo(newStatus);
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
                binding.imageviewAlbumCover.setImageResource(R.drawable.track1);
                md.stop();
                md = MediaPlayer.create(this, R.raw.track1);
                md.start();
                break;
            case 1:
                binding.imageviewAlbumCover.setImageResource(R.drawable.track2);
                md.stop();
                md = MediaPlayer.create(this, R.raw.track2);
                md.start();
                break;
            case 2:
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
                        newAverage = ((binding.progressbarAverageRating1.getProgress() * Integer.valueOf(binding.textviewNumVotes1.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
                    }
                    else
                    {
                        newAverage = binding.ratingbarVoterRating.getProgress();
                    }
                    binding.textviewNumVotes1.setText(String.valueOf(newNumVotes));
                    binding.progressbarAverageRating1.setProgress(newAverage);
                    break;
                case R.id.radio_song2:
                    newNumVotes = Integer.valueOf(binding.textviewNumVotes2.getText().toString()) + 1;
                    if(newNumVotes>1)
                    {
                        newAverage = ((binding.progressbarAverageRating2.getProgress() * Integer.valueOf(binding.textviewNumVotes2.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
                    }
                    else
                    {
                        newAverage = binding.ratingbarVoterRating.getProgress();
                    }
                    binding.textviewNumVotes2.setText(String.valueOf(newNumVotes));
                    binding.progressbarAverageRating2.setProgress(newAverage);
                    break;
                case R.id.radio_song3:
                    newNumVotes = Integer.valueOf(binding.textviewNumVotes3.getText().toString()) + 1;
                    if(newNumVotes>1)
                    {
                        newAverage = ((binding.progressbarAverageRating3.getProgress() * Integer.valueOf(binding.textviewNumVotes3.getText().toString())) + binding.ratingbarVoterRating.getProgress()) / newNumVotes;
                    }
                    else
                    {
                        newAverage = binding.ratingbarVoterRating.getProgress();
                    }

                    binding.textviewNumVotes3.setText(String.valueOf(newNumVotes));
                    binding.progressbarAverageRating3.setProgress(newAverage);
                    break;
            }
            Log.d("vidit", String.valueOf(newAverage));
            binding.ratingbarVoterRating.setRating(0.0f);
        }
    };
}

