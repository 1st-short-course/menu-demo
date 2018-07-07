package com.example.rany.menudemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.SearchView;
import android.util.Log;
import android.view.ActionMode;
import android.view.ContextMenu;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.PopupMenu;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button btnContextMenu,btnContextualMenu,btnPopupMenu;

    //create contextual menu
    ActionMode mActionMode;
    private ActionMode.Callback mActionModeCallback=new ActionMode.Callback() {
        @Override
        public boolean onCreateActionMode(ActionMode mode, Menu menu) {
            mode.getMenuInflater().inflate(R.menu.context_menu,menu);
            return true;
        }

        @Override
        public boolean onPrepareActionMode(ActionMode mode, Menu menu) {
            return false;
        }

        @Override
        public boolean onActionItemClicked(ActionMode mode, MenuItem item) {
            switch (item.getItemId()){
                case R.id.share:
                    showMessage("share click");
                    return true;
                case R.id.addNewFolder:
                    showMessage("add new folder click");
                    return true;
                case R.id.remove:
                    showMessage("remove click");
                    return true;
            }
            return false;
        }

        @Override
        public void onDestroyActionMode(ActionMode mode) {
            mActionMode=null;
        }
    };

    /*
    * end contextual menu
    * */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnContextMenu=findViewById(R.id.btnContextMenu);
        btnContextualMenu=findViewById(R.id.btnContextualMenu);
        btnPopupMenu=findViewById(R.id.btnPopupMenu);
        //register context menu
        registerForContextMenu(btnContextMenu);

        //create event for contextual menu
        btnContextualMenu.setOnLongClickListener(new View.OnLongClickListener() {
            @Override
            public boolean onLongClick(View v) {
                if(mActionMode!=null)
                    return  false;

                mActionMode=MainActivity.this.startActionMode(mActionModeCallback);
                return true;
            }
        });

        btnPopupMenu.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                PopupMenu menu =new PopupMenu(MainActivity.this,btnContextMenu);
                menu.inflate(R.menu.context_menu);
                menu.show();
                menu.setOnMenuItemClickListener(new PopupMenu.OnMenuItemClickListener() {
                    @Override
                    public boolean onMenuItemClick(MenuItem item) {
                        switch (item.getItemId()){
                            case R.id.share:
                                showMessage("share click");
                                return true;
                            case R.id.addNewFolder:
                                showMessage("add new folder click");
                                return true;
                            case R.id.remove:
                                showMessage("remove click");
                                return true;
                        }
                        return false;
                    }
                });
            }
        });
    }

    //create context menu
    @Override
    public void onCreateContextMenu(ContextMenu menu, View v, ContextMenu.ContextMenuInfo menuInfo) {
        getMenuInflater().inflate(R.menu.context_menu,menu);
        super.onCreateContextMenu(menu, v, menuInfo);
    }

    //handle context menu when user click items
    @Override
    public boolean onContextItemSelected(MenuItem item) {
        switch (item.getItemId()){
            case R.id.share:
                showMessage("share click");
                return true;
            case R.id.addNewFolder:
                showMessage("add new folder click");
                return true;
            case R.id.remove:
                showMessage("remove click");
                return true;
        }
        return false;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
       getMenuInflater().inflate(R.menu.option_menu,menu);


        MenuItem  menuItem=menu.findItem(R.id.search);
        SearchView searchView= (SearchView) menuItem.getActionView();
        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                showMessage(query);
                return true;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                Log.e(TAG, "onQueryTextChange: "+ newText);
                return true;
            }
        });


        return super.onCreateOptionsMenu(menu);
    }

    private static final String TAG = "MainActivity";
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        //this.showMessage("item menu is clicked");
        switch (item.getItemId()){
            case R.id.newTab:
                this.showMessage("new Tab is clicked");
                return true;
            case R.id.recent:
                this.showMessage("recent is clicked");
                return true;
            case R.id.download:
                this.showMessage("download is clicked");
                return true;
            case R.id.share:
                this.showMessage("share is clicked");
                return true;
            case R.id.find:
                this.showMessage("find is clicked");
                return true;
        }
        return super.onOptionsItemSelected(item);
    }
    void showMessage(String smg){
        Toast.makeText(this, smg, Toast.LENGTH_SHORT).show();
    }


}
