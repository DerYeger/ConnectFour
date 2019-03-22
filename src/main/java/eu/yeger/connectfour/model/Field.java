package eu.yeger.connectfour.model;

import java.beans.PropertyChangeSupport;

import java.beans.PropertyChangeListener;

public class Field  
{

   public static final String PROPERTY_player = "player";

   private Player player = null;

   public Player getPlayer()
   {
      return this.player;
   }

   public Field setPlayer(Player value)
   {
      if (this.player != value)
      {
         Player oldValue = this.player;
         if (this.player != null)
         {
            this.player = null;
            oldValue.withoutFields(this);
         }
         this.player = value;
         if (value != null)
         {
            value.withFields(this);
         }
         firePropertyChange("player", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_top = "top";

   private Field top = null;

   public Field getTop()
   {
      return this.top;
   }

   public Field setTop(Field value)
   {
      if (this.top != value)
      {
         Field oldValue = this.top;
         if (this.top != null)
         {
            this.top = null;
            oldValue.setBottom(null);
         }
         this.top = value;
         if (value != null)
         {
            value.setBottom(this);
         }
         firePropertyChange("top", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_bottom = "bottom";

   private Field bottom = null;

   public Field getBottom()
   {
      return this.bottom;
   }

   public Field setBottom(Field value)
   {
      if (this.bottom != value)
      {
         Field oldValue = this.bottom;
         if (this.bottom != null)
         {
            this.bottom = null;
            oldValue.setTop(null);
         }
         this.bottom = value;
         if (value != null)
         {
            value.setTop(this);
         }
         firePropertyChange("bottom", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_left = "left";

   private Field left = null;

   public Field getLeft()
   {
      return this.left;
   }

   public Field setLeft(Field value)
   {
      if (this.left != value)
      {
         Field oldValue = this.left;
         if (this.left != null)
         {
            this.left = null;
            oldValue.setRight(null);
         }
         this.left = value;
         if (value != null)
         {
            value.setRight(this);
         }
         firePropertyChange("left", oldValue, value);
      }
      return this;
   }



   public static final String PROPERTY_right = "right";

   private Field right = null;

   public Field getRight()
   {
      return this.right;
   }

   public Field setRight(Field value)
   {
      if (this.right != value)
      {
         Field oldValue = this.right;
         if (this.right != null)
         {
            this.right = null;
            oldValue.setLeft(null);
         }
         this.right = value;
         if (value != null)
         {
            value.setLeft(this);
         }
         firePropertyChange("right", oldValue, value);
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



   public void removeYou()
   {
      this.setGame(null);
      this.setPlayer(null);
      this.setTop(null);
      this.setBottom(null);
      this.setLeft(null);
      this.setRight(null);

   }




   public static final String PROPERTY_game = "game";

   private Game game = null;

   public Game getGame()
   {
      return this.game;
   }

   public Field setGame(Game value)
   {
      if (this.game != value)
      {
         Game oldValue = this.game;
         if (this.game != null)
         {
            this.game = null;
            oldValue.withoutFields(this);
         }
         this.game = value;
         if (value != null)
         {
            value.withFields(this);
         }
         firePropertyChange("game", oldValue, value);
      }
      return this;
   }





}