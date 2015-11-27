package com.example.strong.remotecontrol.Fragment;


import android.os.Bundle;
import android.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;

import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.StringRequest;
import com.android.volley.toolbox.Volley;
import com.example.strong.remotecontrol.R;

/**
 * A simple {@link Fragment} subclass.
 */
public class RemoteControlFragment extends Fragment {

    private TextView mSelectedTextView;
    private TextView mTypingTextView;


    public RemoteControlFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view=inflater.inflate(R.layout.fragment_remote_control, container, false);

//        RequestQueue mQueue= Volley.newRequestQueue(getActivity());
//        StringRequest stringRequest = new StringRequest("http://www.baidu.com",
//                new Response.Listener<String>() {
//                    @Override
//                    public void onResponse(String response) {
//                        Log.d("TAG", response);
//                    }
//                }, new Response.ErrorListener() {
//            @Override
//            public void onErrorResponse(VolleyError error) {
//                Log.e("TAG", error.getMessage(), error);
//            }
//        });
//        mQueue.add(stringRequest);

        mSelectedTextView=(TextView)view.findViewById(R.id.fragment_remote_control_selectedTextView);
        mTypingTextView=(TextView)view.findViewById(R.id.fragment_remote_control_typingTextView);
        View.OnClickListener numberButtonListener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                TextView textView=(TextView)v;
                String text=textView.getText().toString();
                String typing=mTypingTextView.getText().toString();
                if(typing.equals("0")){
                    mTypingTextView.setText(text);
                }else {
                    mTypingTextView.setText(typing+text);
                }
            }
        };

        TableLayout tableLayout=(TableLayout)view.findViewById(R.id.fragment_remote_control_tableLayout);
        int number=1;


        for (int i=2;i<tableLayout.getChildCount()-1;i++){
            TableRow tableRow=(TableRow) tableLayout.getChildAt(i);
            for (int j=0;j<tableRow.getChildCount();j++){
                Button button=(Button)tableRow.getChildAt(j);
                button.setText(""+number);
                button.setOnClickListener(numberButtonListener);
                number++;
            }
        }

        TableRow tableRow=(TableRow) tableLayout.getChildAt(tableLayout.getChildCount()-1);

        Button deleteButton=(Button) tableRow.getChildAt(0);
        deleteButton.setText("Del");
        deleteButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mTypingTextView.setText("0");
            }
        });

        Button zeroButton=(Button) tableRow.getChildAt(1);
        zeroButton.setText("0");
        zeroButton.setOnClickListener(numberButtonListener);

        Button enterButton=(Button) tableRow.getChildAt(2);
        enterButton.setText("Enter");
        enterButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CharSequence typing=mTypingTextView.getText();
                if(typing.length()>0){
                    mSelectedTextView.setText(typing);
                }
                mTypingTextView.setText("0");
            }
        });


        return view;
    }

}
