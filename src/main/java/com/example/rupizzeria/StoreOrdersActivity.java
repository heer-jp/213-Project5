package com.example.rupizzeria;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class StoreOrdersActivity extends AppCompatActivity {

    private ListView storeOrdersLV;
    private Button cancelBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        storeOrdersLV = (ListView) findViewById(R.id.storeOrdersLV);
        storeOrdersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrdersActivity.this);
                alert.setMessage("Remove pizza").setTitle("Remove pizza");
                alert.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeOrder(position);
                    }
                });
                alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
//                        displayToast(getString(R.string.itemno));
                        System.out.println("did not remove pizza");
                    }
                });
                AlertDialog dialog = alert.create();
                dialog.show();
            }
        });
        fillListView();

        cancelBtn = (Button) findViewById(R.id.cancel);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });
    }

    private void removeOrder(int position) {
        MainActivity.storeOrders.removeOrder(MainActivity.storeOrders.getOrder(position));
        if (MainActivity.storeOrders.getOrders().size() > 0) {
            fillListView();
        } else {
            finish();
        }
    }

    public void fillListView() {
        ArrayAdapter<Order> list = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrders.getOrders());
        storeOrdersLV.setAdapter(list);
    }
}