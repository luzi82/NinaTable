package com.luzi82.ninatable.test;

import org.junit.Test;

public class NinaTableTest {
    @Test
    public void testCreateTable() {
        Table.newTable()
            .addColumn("int_col", Table.DataType.INTEGER)
            .addColumn("string_col", Table.DataType.STRING)
            .addColumn("float_col", Table.DataType.FLOAT)
            .create()
        ;
    }
    
    @Test
    public void testInsertRow() {
        Table table = Table.newTable()
            .addColumn("int_col", Table.DataType.INTEGER)
            .addColumn("string_col", Table.DataType.STRING)
            .addColumn("float_col", Table.DataType.FLOAT)
            .create()
        ;
        
        assertTrue("", table.rowCount()==0);
        
        table.newRow()
            .setColumn("int_col", 123)
            .setColumn("string_col", "123")
            .setColumn("float_col", 1.23f)
            .insert();

        assertTrue("", table.rowCount()==1);
    }

    @Test
    public void testQueryTable0() {
        Table table = Table.newTable()
            .addColumn("int_col", Table.DataType.INTEGER)
            .addColumn("string_col", Table.DataType.STRING)
            .addColumn("float_col", Table.DataType.FLOAT)
            .newIndex("k_int_s"   ).addColumn("int_col",    Table.IndexOrderType.SORT).add()
            .newIndex("k_int_h"   ).addColumn("int_col",    Table.IndexOrderType.HASH).add()
            .newIndex("k_string_h").addColumn("string_col", Table.IndexOrderType.HASH).add()
            .newIndex("k_float_s" ).addColumn("float_col",  Table.IndexOrderType.SORT).add()
            .create()
        ;
        
        assertTrue("", table.rowCount()==0);
        
        table.newRow()
            .setColumn("int_col", 123)
            .setColumn("string_col", "123")
            .setColumn("float_col", 123f)
            .insert()
        ;

        assertTrue("", table.rowCount()==1);
        
        TableRow[] tableRowAry;

        tableRowAry = table.newQuery("k_int_s").eq("int_col",123).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").eq("int_col",123).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_int_s").lt("int_col",122).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").lt("int_col",123).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").lt("int_col",124).select();
        assertTrue("", tableRowAry.length==1);

        tableRowAry = table.newQuery("k_int_s").lte("int_col",122).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").lte("int_col",123).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").lte("int_col",124).select();
        assertTrue("", tableRowAry.length==1);

        tableRowAry = table.newQuery("k_int_s").gt("int_col",122).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",123).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",124).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_int_s").gte("int_col",122).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gte("int_col",123).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gte("int_col",124).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_string_h").eq("string_col","123").select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_string_h").eq("string_col","321").select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_float_s").eq("float_col",123f,0.001f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").eq("float_col",321f,0.001f).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_float_s").lt("float_col",122f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").lt("float_col",123f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").lt("float_col",124f).select();
        assertTrue("", tableRowAry.length==1);

        tableRowAry = table.newQuery("k_float_s").lte("float_col",122f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").lte("float_col",123f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").lte("float_col",124f).select();
        assertTrue("", tableRowAry.length==1);

        tableRowAry = table.newQuery("k_float_s").gt("float_col",122f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gt("float_col",123f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").gt("float_col",124f).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_float_s").gte("float_col",122f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gte("float_col",123f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gte("float_col",124f).select();
        assertTrue("", tableRowAry.length==0);
    }

    @Test
    public void testQueryTable1() {
        Table table = Table.newTable()
            .addColumn("int_col", Table.DataType.INTEGER)
            .addColumn("string_col", Table.DataType.STRING)
            .addColumn("float_col", Table.DataType.FLOAT)
            .newIndex("k_int_s"   ).addColumn("int_col",    Table.IndexOrderType.SORT).add()
            .newIndex("k_int_h"   ).addColumn("int_col",    Table.IndexOrderType.HASH).add()
            .newIndex("k_string_h").addColumn("string_col", Table.IndexOrderType.HASH).add()
            .newIndex("k_float_s" ).addColumn("float_col",  Table.IndexOrderType.SORT).add()
            .create()
        ;
        
        assertTrue("", table.rowCount()==0);
        
        table.newRow()
            .setColumn("int_col", 123)
            .setColumn("string_col", "123")
            .setColumn("float_col", 123f)
            .insert()
        ;

        assertTrue("", table.rowCount()==1);
        
        TableRow[] tableRowAry;

        tableRowAry = table.newQuery("k_int_s").gt("int_col",122).lt("int_col",124).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",122).eq("int_col",123).lt("int_col",124).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gte("int_col",122).eq("int_col",123).lte("int_col",124).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",124).eq("int_col",123).lt("int_col",124).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",122).eq("int_col",321).lt("int_col",124).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_int_s").gt("int_col",122).eq("int_col",123).lt("int_col",122).select();
        assertTrue("", tableRowAry.length==0);

        tableRowAry = table.newQuery("k_float_s").gt("int_col",122f).lt("int_col",124f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gt("int_col",122f).eq("int_col",123f,0.001f).lt("int_col",124f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gte("int_col",122f).eq("int_col",123f,0.001f).lte("int_col",124f).select();
        assertTrue("", tableRowAry.length==1);
        tableRowAry = table.newQuery("k_float_s").gt("int_col",124f).eq("int_col",123f,0.001f).lt("int_col",124f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").gt("int_col",122f).eq("int_col",321f,0.001f).lt("int_col",124f).select();
        assertTrue("", tableRowAry.length==0);
        tableRowAry = table.newQuery("k_float_s").gt("int_col",122f).eq("int_col",123f,0.001f).lt("int_col",122f).select();
        assertTrue("", tableRowAry.length==0);
    }
}
