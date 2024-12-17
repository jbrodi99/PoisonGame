package view.components;

import model.interfaces.IGameMatchStatusPublic;
import model.logic.GameMatchStatus;

import javax.swing.table.AbstractTableModel;
import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.List;

public class MyTblModel extends AbstractTableModel {

    private List<String> columnNames = new ArrayList<>();
    private List<String> methodName = new ArrayList<>();
    private List<IGameMatchStatusPublic> data;

    public MyTblModel(List<IGameMatchStatusPublic> data) {
        this.data = data;
        for (Field field : GameMatchStatus.class.getDeclaredFields()){
            columnNames.add(field.getName());

        }
        for (Method method : GameMatchStatus.class.getDeclaredMethods()){
            methodName.add(method.getName());

        }
        this.columnNames = columnNames.stream().sorted().toList();
        this.methodName = methodName.stream().sorted().toList();
    }

    @Override
    public int getRowCount() {
        if (!data.isEmpty()){
            return data.size();
        }
        return 0;
    }

    @Override
    public int getColumnCount() {
        return GameMatchStatus.class.getDeclaredMethods().length - 1;
    }

    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Object ret = null;
        try {
            Method method = data.get(rowIndex).getClass().getMethod(methodName.get(columnIndex),null);
            System.out.println(method.getName());
            ret = method.invoke(data.get(rowIndex), null);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
        return ret;
    }

    @Override
    public String getColumnName(int column) {
        return columnNames.get(column);
    }

    public List<IGameMatchStatusPublic> getData() {
        return data;
    }
}
