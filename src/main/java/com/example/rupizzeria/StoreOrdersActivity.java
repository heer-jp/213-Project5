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

/**
 * Class that handles output for all pizza order(s).
 * Allows all pizza order(s) to be viewed and or cancelled.
 * @author Sai Maduri, Heer Patel
 */
public class StoreOrdersActivity extends AppCompatActivity {

    private ListView storeOrdersLV;
    private Button cancelBtn;

    /**
     * Open activity_store_orders.
     * Dispaly all store orders with ability to remove orders.
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_store_orders);

        storeOrdersLV = (ListView) findViewById(R.id.storeOrdersLV);
        storeOrdersLV.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                AlertDialog.Builder alert = new AlertDialog.Builder(StoreOrdersActivity.this);
                alert.setMessage(R.string.remove_pizza_text).setTitle(R.string.remove_pizza_title);
                alert.setPositiveButton(R.string.yes, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {
                        removeOrder(position);
                    }
                });
                alert.setNegativeButton(R.string.no, new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int which) {

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

    /**
     * Remove order from list.
     * @param position of order.
     */
    private void removeOrder(int position) {
        MainActivity.storeOrders.removeOrder(MainActivity.storeOrders.getOrder(position));
        if (MainActivity.storeOrders.getOrders().size() > 0) {
            fillListView();
        } else {
            finish();
        }
    }

    /**
     * Fill display with pizza order list.
     */
    public void fillListView() {
        ArrayAdapter<Order> list = new ArrayAdapter<Order>(this, android.R.layout.simple_list_item_1, MainActivity.storeOrders.getOrders());
        storeOrdersLV.setAdapter(list);
    }
}