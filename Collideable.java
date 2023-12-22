public interface Collideable<T>
{
  boolean didCollideLeft(T obj);  
  boolean didCollideRight(T obj);  
  boolean didCollide(T obj);
}