package com.luzi82.ninatable.test;

import org.junit.Test;

public class NinaTableTest {
    @Test
    public void testCreateTable() {
        TableDefination tableDefination = new TableDefination();
        tableDefination.addColumn("int_col", TableDefination.DataType.INTEGER);
        tableDefination.addColumn("string_col", TableDefination.DataType.STRING);
        tableDefination.addColumn("float_col", TableDefination.DataType.FLOAT);
        
        new Table(tableDefination);
    }
    
    @Test
    public void testInsertRow() {
        TableDefination tableDefination = new TableDefination();
        tableDefination.addColumn("int_col", TableDefination.DataType.INTEGER);
        tableDefination.addColumn("string_col", TableDefination.DataType.STRING);
        tableDefination.addColumn("float_col", TableDefination.DataType.FLOAT);
        
        Table table = new Table(tableDefination);
        
        assertTrue("", table.rowCount()==0);
        
        TableRow tableRow = new TableRow(tableDefination);
        tableRow.setColumn("int_col", 123);
        tableRow.setColumn("string_col", "123");
        tableRow.setColumn("float_col", 1.23f);
        table.insert(tableRow);
        
        assertTrue("", table.rowCount()==1);
    }

    @Test
    public void testQueryTable0() {
        TableDefination tableDefination = new TableDefination();
        tableDefination.addColumn("int_col", TableDefination.DataType.INTEGER);
        tableDefination.addColumn("string_col", TableDefination.DataType.STRING);
        tableDefination.addColumn("float_col", TableDefination.DataType.FLOAT);
        
        TableIndex tableIndex;
        tableIndex=new TableIndex("k_int_s");
        tableIndex.addColumn("int_col", TableIndex.OrderType.SORT);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_int_h");
        tableIndex.addColumn("int_col", TableIndex.OrderType.HASH);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_string_h");
        tableIndex.addColumn("string_col", TableIndex.OrderType.HASH);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_float_s");
        tableIndex.addColumn("float_col", TableIndex.OrderType.SORT);
        tableDefination.addIndex(tableIndex);

        Table table = new Table(tableDefination);
        
        assertTrue("", table.rowCount()==0);
        
        TableRow tableRow = new TableRow(tableDefination);
        tableRow.setColumn("int_col", 123);
        tableRow.setColumn("string_col", "123");
        tableRow.setColumn("float_col", 1.23f);
        table.insert(tableRow);

        assertTrue("", table.rowCount()==1);
        
        TableRow[] tableRowAry;
        
        TableQuery tableQuery;

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addEqual("int_col",123);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addEqual("int_col",321);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",122);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",123);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLessEqual("int_col",122);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLessEqual("int_col",123);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLessEqual("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreater("int_col",122);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreater("int_col",123);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreater("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreaterEqual("int_col",122);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreaterEqual("int_col",123);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addGreaterEqual("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_string_h");
        tableQuery.addEqual("string_col","123");
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_string_h");
        tableQuery.addEqual("string_col","321");
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addEqual("float_col",123f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addEqual("float_col",321f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",122f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",123f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLessEqual("float_col",122f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLessEqual("float_col",123f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLessEqual("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreater("float_col",122f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreater("float_col",123f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreater("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreaterEqual("float_col",122f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreaterEqual("float_col",123f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addGreaterEqual("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==0);
    }

    @Test
    public void testQueryTable1() {
        TableDefination tableDefination = new TableDefination();
        tableDefination.addColumn("int_col", TableDefination.DataType.INTEGER);
        tableDefination.addColumn("string_col", TableDefination.DataType.STRING);
        tableDefination.addColumn("float_col", TableDefination.DataType.FLOAT);
        
        TableIndex tableIndex;
        tableIndex=new TableIndex("k_int_s");
        tableIndex.addColumn("int_col", TableIndex.OrderType.SORT);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_int_h");
        tableIndex.addColumn("int_col", TableIndex.OrderType.HASH);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_string_h");
        tableIndex.addColumn("string_col", TableIndex.OrderType.HASH);
        tableDefination.addIndex(tableIndex);
        tableIndex=new TableIndex("k_float_s");
        tableIndex.addColumn("float_col", TableIndex.OrderType.SORT);
        tableDefination.addIndex(tableIndex);

        Table table = new Table(tableDefination);
        
        assertTrue("", table.rowCount()==0);
        
        TableRow tableRow = new TableRow(tableDefination);
        tableRow.setColumn("int_col", 123);
        tableRow.setColumn("string_col", "123");
        tableRow.setColumn("float_col", 1.23f);
        table.insert(tableRow);

        assertTrue("", table.rowCount()==1);
        
        TableRow[] tableRowAry;
        
        TableQuery tableQuery;

        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",122);
        tableQuery.addGreater("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",122);
        tableQuery.addEqual("int_col",123);
        tableQuery.addGreater("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLessEqual("int_col",122);
        tableQuery.addEqual("int_col",123);
        tableQuery.addGreaterEqual("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",124);
        tableQuery.addEqual("int_col",123);
        tableQuery.addGreater("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",122);
        tableQuery.addEqual("int_col",321);
        tableQuery.addGreater("int_col",124);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_int_s");
        tableQuery.addLess("int_col",122);
        tableQuery.addEqual("int_col",123);
        tableQuery.addGreater("int_col",122);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);

        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",122f);
        tableQuery.addGreater("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",122f);
        tableQuery.addEqual("float_col",123f);
        tableQuery.addGreater("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLessEqual("float_col",122f);
        tableQuery.addEqual("float_col",123f);
        tableQuery.addGreaterEqual("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",124f);
        tableQuery.addEqual("float_col",123f);
        tableQuery.addGreater("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",122f);
        tableQuery.addEqual("float_col",321f);
        tableQuery.addGreater("float_col",124f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
        tableQuery = new TableQuery(TableDefination, "k_float_s");
        tableQuery.addLess("float_col",122f);
        tableQuery.addEqual("float_col",123f);
        tableQuery.addGreater("float_col",122f);
        tableRowAry = table.select(tableQuery);
        assertTrue("", tableRowAry.length==1);
    }
}
