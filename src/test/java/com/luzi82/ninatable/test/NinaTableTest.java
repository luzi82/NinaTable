package com.luzi82.ninatable.test;

import org.junit.Test;

public class NinaTableTest {
    @Test
    public void testCreateTable() {
        TableDefination tableDefination = new TableDefination()
            .addColumn("int_col", TableDefination.DataType.INTEGER)
            .addColumn("string_col", TableDefination.DataType.STRING)
            .addColumn("float_col", TableDefination.DataType.FLOAT);
        
        Table.create(tableDefination);
    }
    
    @Test
    public void testInsertRow() {
        TableDefination tableDefination = new TableDefination()
            .addColumn("int_col", TableDefination.DataType.INTEGER)
            .addColumn("string_col", TableDefination.DataType.STRING)
            .addColumn("float_col", TableDefination.DataType.FLOAT);
        
        Table table = Table.create(tableDefination);
        
        assertTrue("", table.rowCount()==0);
        
        table.insertRow(TableRow.create(tableDefination)
            .setColumn("int_col", 123)
            .setColumn("string_col", "123")
            .setColumn("float_col", 1.23f)
        );

        assertTrue("", table.rowCount()==1);
    }

    @Test
    public void testQueryTable() {
        TableDefination tableDefination = new TableDefination()
            .addColumn("int_col", TableDefination.DataType.INTEGER)
            .addColumn("string_col", TableDefination.DataType.STRING)
            .addColumn("float_col", TableDefination.DataType.FLOAT)
            .addIndex(new TableKey("k_int_s").addColumn("int_col", TableKey.KeyType.SORT))
            .addIndex(new TableKey("k_int_h").addColumn("int_col", TableKey.KeyType.HASH))
            .addIndex(new TableKey("k_string_h").addColumn("string_col", TableKey.KeyType.HASH))
            .addIndex(new TableKey("k_float_s").addColumn("float_col", TableKey.KeyType.SORT))
        ;
        
        Table table = Table.create(tableDefination);
        
        assertTrue("", table.rowCount()==0);
        
        table.insertRow(TableRow.create(tableDefination)
            .setColumn("int_col", 123)
            .setColumn("string_col", "123")
            .setColumn("float_col", 1.23f)
        );

        assertTrue("", table.rowCount()==1);
        
        TableQuery tableQuery = TableQuery.create(tableDefination);
        tableRow.setColumn("int_col", 123);
        tableRow.setColumn("string_col", "123");
        tableRow.setColumn("float_col", 1.23);
    }
}
