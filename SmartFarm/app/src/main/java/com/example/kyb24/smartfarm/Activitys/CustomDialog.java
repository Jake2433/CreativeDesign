package com.example.kyb24.smartfarm.Activitys;

/**
 * Created by kyb24 on 2017-04-09.
 */
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.kyb24.smartfarm.R;

public final class CustomDialog extends Dialog {

    private EditText _valueField ;
    private OnDismissListener _listener ;
    public CustomDialog(Context context) {
        super(context);
    }

    @Override
    public void onCreate( Bundle $savedInstanceState ) {
        super.onCreate( $savedInstanceState ) ;
        setContentView( R.layout.value_dialog ) ;

        _valueField = (EditText) findViewById( R.id.logTxt ) ;
        Button btn = (Button) findViewById( R.id.dismissBtn ) ;

        btn.setOnClickListener( new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                if( _listener == null ) {} else {
                    _listener.onDismiss( CustomDialog.this ) ;
                }
                dismiss() ;
            }
        }) ;
    }

    public void setOnDismissListener( OnDismissListener $listener ) {
        _listener = $listener ;
    }

    public String getValue() {
        return _valueField.getText().toString();
    }
}