package eu.yeger.connectfour.model.tables;

import java.util.ArrayList;
import java.util.LinkedHashMap;

public class StringTable
{
   private ArrayList<ArrayList<Object> > table = new ArrayList<>();

   public ArrayList<ArrayList<Object> > getTable()
   {
      return table;
   }

   public void setTable(ArrayList<ArrayList<Object> > table)
   {
      this.table = table;
   }

   private String columnName = null;

   public String getColumnName()
   {
      return columnName;
   }

   public void setColumnName(String columnName)
   {
      this.columnName = columnName;
   }

   LinkedHashMap<String, Integer> columnMap = new LinkedHashMap<>();

   public void setColumnMap(LinkedHashMap<String, Integer> columnMap)
   {
      this.columnMap = columnMap;
   }

   public StringTable(String... start)
   {
      columnName = "A";
      columnMap.put(columnName, 0);
      for (String current : start)
      {
         ArrayList<Object> row = new ArrayList<>();
         row.add(current);
         table.add(row);
      }
   }

   public ArrayList<String> toList()
   {
      ArrayList<String> result = new ArrayList<>();
      for (ArrayList<Object> row : table)
      {
         String value =  (String) row.get(columnMap.get(columnName));
         result.add(value);
      }
      return result;
   }

   public String join(String seperator)
   {
      StringBuilder buf = new StringBuilder();
      boolean first = true;
      for (ArrayList<Object> row : table)
      {
         String value =  (String) row.get(columnMap.get(columnName));
         if ( ! first)
         {
            buf.append(seperator);
         }
         first = false;
         buf.append(value);
      }
      return buf.toString();
   }

   @Override
   public String toString()
   {
      StringBuilder buf = new StringBuilder();
      for (String key : columnMap.keySet())
      {
         buf.append(key).append(" \t");
      }
      buf.append("\n");
      for (ArrayList<Object> row : table)
      {
         for (Object cell : row)
         {
            buf.append(cell).append(" \t");
         }
         buf.append("\n");
      }
      buf.append("\n");
      return buf.toString();
   }
}