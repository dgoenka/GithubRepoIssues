package com.divyanshgoenka.omdbsearch.model;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.TextView;

import com.divyanshgoenka.omdbsearch.R;

/**
 * Created by divyanshgoenka on 03/05/17.
 */

public class Detail {

    private String fieldName;
    private String fieldValue;

    public Detail(String fieldName, String fieldValue) {
        this.fieldName = fieldName;
        this.fieldValue = fieldValue;
    }

    public String getFieldName() {
        return fieldName;
    }

    public String getFieldValue() {
        return fieldValue;
    }

    public static class ViewHolder extends RecyclerView.ViewHolder implements AdapterBinder<Detail> {
        private TextView fieldNameTT;
        private TextView fieldValueTT;

        public ViewHolder(View itemView) {
            super(itemView);
            fieldNameTT = (TextView) itemView.findViewById(R.id.field_name);
            fieldValueTT = (TextView) itemView.findViewById(R.id.field_value);
        }

        @Override
        public void onBind(Detail arg) {
            if (arg != null) {
                fieldNameTT.setText(arg.getFieldName());
                fieldValueTT.setText(arg.getFieldValue());
            }
        }

        @Override
        public void onUnbind() {
            fieldNameTT.setText("");
            fieldValueTT.setText("");
        }
    }
}
