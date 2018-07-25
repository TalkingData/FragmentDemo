package com.talkingdata.analytics.demo;


import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;

import com.tendcloud.tenddata.TCAgent;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentTransaction;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.Fragment;

/**
 * Demonstration of hiding and showing fragments.
 */
public class FragmentHideShow extends FragmentActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_hide_show);

        // The content view embeds two fragments; now retrieve them and attach
        // their "hide" button.
        FragmentManager fm = getSupportFragmentManager();
        addShowHideListener(R.id.frag1hide, fm.findFragmentById(R.id.fragment1));
        addShowHideListener(R.id.frag2hide, fm.findFragmentById(R.id.fragment2));
    }

    void addShowHideListener(int buttonId, final Fragment fragment) {
        final Button button = (Button)findViewById(buttonId);
        button.setOnClickListener(new OnClickListener() {
            public void onClick(View v) {
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                if (fragment.isHidden()) {
                    ft.show(fragment);
                    button.setText("Hide");
                    TCAgent.onPageStart(getApplicationContext(), fragment.toString());
                } else {
                    ft.hide(fragment);
                    button.setText("Show");
                    TCAgent.onPageEnd(getApplicationContext(), fragment.toString());
                }
                ft.commit();
            }
        });
    }

    public static class FirstFragment extends Fragment {
        TextView mTextView;

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            ((TextView)tv).setText("The fragment saves and restores this text.");

            // Retrieve the text editor, and restore the last saved state if needed.
            mTextView = (TextView)v.findViewById(R.id.saved);
            if (savedInstanceState != null) {
                mTextView.setText(savedInstanceState.getCharSequence("text"));
            }
            return v;
        }

        @Override
        public void onResume() {
            super.onResume();
            TCAgent.onPageStart(getActivity().getApplicationContext(), this.toString());
        }

        @Override
        public void onPause() {
            if(!this.isHidden()){
                TCAgent.onPageEnd(getActivity().getApplicationContext(), this.toString());
            }
            super.onPause();
        }

        @Override
        public void onSaveInstanceState(Bundle outState) {
            super.onSaveInstanceState(outState);

            // Remember the current text, to restore if we later restart.
            outState.putCharSequence("text", mTextView.getText());
        }
    }

    public static class SecondFragment extends Fragment {

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState) {
            View v = inflater.inflate(R.layout.labeled_text_edit, container, false);
            View tv = v.findViewById(R.id.msg);
            ((TextView)tv).setText("The TextView saves and restores this text.");

            // Retrieve the text editor and tell it to save and restore its state.
            // Note that you will often set this in the layout XML, but since
            // we are sharing our layout with the other fragment we will customize
            // it here.
            return v;
        }

        @Override
        public void onResume() {
            super.onResume();
            TCAgent.onPageStart(getActivity().getApplicationContext(), this.toString());
        }

        @Override
        public void onPause() {
            super.onPause();
            if(!this.isHidden()){
                TCAgent.onPageEnd(getActivity().getApplicationContext(), this.toString());
            }
            super.onPause();
        }
    }
}
