package eu.yeger.connectfour.model;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Player  
{

   public static final String PROPERTY_color = "color";

   private String color;

   public String getColor()
   {
      return color;
   }

   public Player setColor(String value)
   {
      if (value == null ? this.color != null : ! value.equals(this.color))
      {
         String oldValue = this.color;
         this.color = value;
         firePropertyChange("color", oldValue, value);
      }
      return this;
   }


public static final String PROPERTY_game = "game";

private Game game = null;

public Game getGame()
   {
      return this.game;
   }

public Player setGame(Game value)
   {
      if (this.game != value)
      {
         Game oldValue = this.game;
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutPlayers(this);
         }
         this.game = value;
         if (value != null)
         {
            value.withPlayers(this);
         }
         firePropertyChange("game", oldValue, value);
      }
      return this;
   }


   public static final String PROPERTY_currentGame = "currentGame";

   private Game currentGame = null;

   public Game getCurrentGame()
   {
      return this.currentGame;
   }

   public Player setCurrentGame(Game value)
   {
      if (this.currentGame != value)
      {
         Game oldValue = this.currentGame;
         if (this.currentGame != null)
         {
            this.currentGame = null;
            oldValue.setCurrentPlayer(null);
         }
         this.currentGame = value;
         if (value != null)
         {
            value.setCurrentPlayer(this);
         }
         firePropertyChange("currentGame", oldValue, value);
      }
      return this;
   }



   public static final java.util.ArrayList<Field> EMPTY_fields = new java.util.ArrayList<Field>()
   { @Override public boolean add(Field value){ throw new UnsupportedOperationException("No direct add! Use xy.withFields(obj)"); }};


   public static final String PROPERTY_fields = "fields";

   private java.util.ArrayList<Field> fields = null;

   public java.util.ArrayList<Field> getFields()
   {
      if (this.fields == null)
      {
         return EMPTY_fields;
      }

      return this.fields;
   }

   public Player withFields(Object... value)
   {
      if(value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withFields(i);
            }
         }
         else if (item instanceof Field)
         {
            if (this.fields == null)
            {
               this.fields = new java.util.ArrayList<Field>();
            }
            if ( ! this.fields.contains(item))
            {
               this.fields.add((Field)item);
               ((Field)item).setPlayer(this);
               firePropertyChange("fields", null, item);
            }
         }
         else throw new IllegalArgumentException();
      }
      return this;
   }



   public Player withoutFields(Object... value)
   {
      if (this.fields == null || value==null) return this;
      for (Object item : value)
      {
         if (item == null) continue;
         if (item instanceof java.util.Collection)
         {
            for (Object i : (java.util.Collection) item)
            {
               this.withoutFields(i);
            }
         }
         else if (item instanceof Field)
         {
            if (this.fields.contains(item))
            {
               this.fields.remove((Field)item);
               ((Field)item).setPlayer(null);
               firePropertyChange("fields", item, null);
            }
         }
      }
      return this;
   }


   protected PropertyChangeSupport listeners = null;

   public boolean firePropertyChange(String propertyName, Object oldValue, Object newValue)
   {
      if (listeners != null)
      {
         listeners.firePropertyChange(propertyName, oldValue, newValue);
         return true;
      }
      return false;
   }

   public boolean addPropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(listener);
      return true;
   }

   public boolean addPropertyChangeListener(String propertyName, PropertyChangeListener listener)
   {
      if (listeners == null)
      {
         listeners = new PropertyChangeSupport(this);
      }
      listeners.addPropertyChangeListener(propertyName, listener);
      return true;
   }

   public boolean removePropertyChangeListener(PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(listener);
      }
      return true;
   }

   public boolean removePropertyChangeListener(String propertyName,PropertyChangeListener listener)
   {
      if (listeners != null)
      {
         listeners.removePropertyChangeListener(propertyName, listener);
      }
      return true;
   }

   @Override
   public String toString()
   {
      StringBuilder result = new StringBuilder();

      result.append(" ").append(this.getColor());


      return result.substring(1);
   }

   public void removeYou()
   {
      this.setGame(null);
      this.setCurrentGame(null);

      this.withoutFields(this.getFields().clone());


   }


}