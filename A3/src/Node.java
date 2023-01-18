/**
 * This class was downloaded from BrightSpace
 */

public class Node<T>
{
	private T data;
	private Node<T> next;

	//Constructor
	public Node(T data, Node<T> next)
	{
		this.data = data;
		this.next = next;
	}

	//Getters and Setters
	public T getData()
	{
		return data;
	}
	public Node<T> getNext()
	{
		return this.next;
	}
	public void setData(T data)
	{
		this.data = data;
	}
	public void setNext(Node<T> next)
	{
		this.next = next;
	}
	
}
