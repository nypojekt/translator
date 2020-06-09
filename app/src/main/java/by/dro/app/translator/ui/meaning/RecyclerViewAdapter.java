//package by.dro.app.translator.ui.meaning;
//
//import android.provider.ContactsContract;
//import android.view.LayoutInflater;
//import android.view.View;
//import android.view.ViewGroup;
//
//import androidx.recyclerview.widget.RecyclerView;
//
//import java.util.List;
//
//public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
//    private View vv;
//    private List<Data> allRecords;
//    //список всех данных
//    public RecyclerViewAdapter(List<ContactsContract.Contacts.Data> records) {
//        allRecords = records;     }
//        @Override
//        public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
//        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.item, viewGroup, false);
//        return new RecyclerViewAdapter.ViewHolder(v);     }
//
//
//        @Override
//        public void onBindViewHolder(final RecyclerViewAdapter.ViewHolder viewHolder, int i) {
//        Data record = allRecords.get(i);
//        String value = record.getValueText();
//        int id = record.getValueId();
//        int parentId = record.getParentId();
//        final int position = i;
//        final String text = "#" + id + ": " + value + " (id родительского элемента: " + parentId + ")";          //покажем или скроем элемент, если он дочерни
//            if (parentId >= 0) {             //видимость делаем по параметру родительского элемента
//                 setVisibility(viewHolder.item, allRecords.get(parentId).isChildVisibility(), parentId);         }
//            else { //элемент не дочерний, показываем его
//                 setVisibility(viewHolder.item, true, parentId);         }          //покажем или скроем иконку деревовидного списка
//             if (record.isItemParent()) {             viewHolder.iconTree.setVisibility(View.VISIBLE);             //показываем нужную иконку
//                  if (record.isChildVisibility()) //показываются дочерние элементы
//                       viewHolder.iconTree.setBackgroundResource(R.drawable.icon_show);
//                  else //скрыты дочерние элементы
//                  viewHolder.iconTree.setBackgroundResource(R.drawable.icon_hide);         }
//             else //элемент не родительский
//             viewHolder.iconTree.setVisibility(View.GONE);          //устанавливаем текст элемента
//             if (!TextUtils.isEmpty(value)) {
//                 viewHolder.valueText.setText(value);         }          //добавляем обработку нажатий по значению
//             viewHolder.valueText.setOnClickListener(new View.OnClickListener() {
//                 @Override
//                 public void onClick(View view) {
//                     Data dataItem = allRecords.get(position);
//                     if (dataItem.isItemParent()) { //нажали по родительскому элементу, меняем видимость дочерних элементов
//                          dataItem.setChildVisibility(!dataItem.isChildVisibility());                     n
//                     otifyDataSetChanged();                 }
//                     else { //нажали по обычному элементу, обрабатываем как нужно
//                          Snackbar snackbar = Snackbar.make(vv, text, Snackbar.LENGTH_LONG);
//                          snackbar.show();                 }             }         });     }
//                          //установка видимости элемента
//
//private void setVisibility(View curV,  boolean visible, int parentId) {         //найдем блок, благодаря которому будем сдвигать текст
//         LinearLayout vPadding = curV.findViewById(R.id.block_text);
//         LinearLayout.LayoutParams params;
//         if (visible) {             params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
//         if (vPadding != null) {
//             if (parentId >= 0) { //это дочерний элемент, делаем отступ
//                  vPadding.setPadding(80, 0, 0, 0);                 }
//             else {                     vPadding.setPadding(0, 0, 0, 0);                 }             }         }
//         else             params = new LinearLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0);
//         curV.setLayoutParams(params);     }
//
//         @Override
//         public int getItemCount() {         return allRecords.size();     }
//
//         class ViewHolder extends RecyclerView.ViewHolder {
//        private LinearLayout item;
//        private TextView valueText;
//        private AppCompatImageView iconTree;
//        public ViewHolder(View itemView) {
//            super(itemView);
//            vv = itemView;
//            item = vv.findViewById(R.id.id_item);
//            valueText = vv.findViewById(R.id.value_name);
//            iconTree = vv.findViewById(R.id.icon_tree);
//        }     } }
//
//
//
//    List<Data> records = new ArrayList<Data>();
////список значений Data record;
//RecyclerViewAdapter adapter;
//int parentId;
//RecyclerView recyclerView = findViewById(R.id.recycler_list);
//record = new Data();
//record.setValueId(1);
//record.setValueText("Родительское значение 1");
//record.setItemParent(true); //родительское значение
//records.add(record);
//parentId = records.size() -1;
//for (int ind = 1; ind <= 3; ind ++) {
//    record = new Data();
//    record.setValueId(ind);
//    record.setValueText("Текст " + ind);
//    record.setParentId(parentId);
//    records.add(record); }
//record = new Data();
//record.setValueId(1);
//record.setValueText("Второе родительское значение");
//record.setItemParent(true); //родительское значение
//records.add(record);
//parentId = records.size() -1;
//for (int ind = 4; ind <= 7; ind ++) {
//    record = new Data();
//    record.setValueId(ind);
//    record.setValueText("Дочерний текст " + ind);
//    record.setParentId(parentId);
//    records.add(record); }
//record = new Data();
//record.setValueId(1);
//record.setValueText("Еще родительское значение");
//record.setItemParent(true); //родительское значение
//records.add(record);
//parentId = records.size() -1;
//for (int ind = 8; ind <= 12; ind ++) {
//    record = new Data();
//    record.setValueId(ind);
//    record.setValueText("Значение " + ind);
//    record.setParentId(parentId);
//    records.add(record); }
//for (int ind = 13; ind <= 18; ind ++) {
//    record = new Data();
//    record.setValueId(ind);
//    record.setValueText("Текст без родителя" + ind);
//    records.add(record); }  for (int ind = 19; ind <= 21; ind ++) {
//    record = new Data();
//    record.setValueId(ind);
//    record.setValueText("Элемент тоже без родителя" + ind);
//    records.add(record); }
//record = new Data(); record.setValueId(1); record.setValueText("Опять родительское значение"); record.setItemParent(true);
////родительское значение
//records.add(record);
//parentId = records.size() -1; for (int ind = 22; ind <= 30; ind ++) {
//    record = new Data();     record.setValueId(ind);
//    record.setValueText("Дочернее: " + ind);     record.setParentId(parentId);     records.add(record); }  for (int ind = 31; ind <= 45; ind ++) {
//    record = new Data();     record.setValueId(ind);     record.setValueText("Последние без родителя " + ind);
//    records.add(record); }  adapter = new RecyclerViewAdapter(records);
//RecyclerView.ItemAnimator itemAnimator = new DefaultItemAnimator();
//LinearLayoutManager layoutManager = new LinearLayoutManager(this);
//recyclerView.setAdapter(adapter); recyclerView.setLayoutManager(layoutManager); recyclerView.setItemAnimator(itemAnimator);
