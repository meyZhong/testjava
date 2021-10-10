class Book{
	private String title ;
	private double price ;
	public Book(String title,double price){
		this.title = title ;
		this.price = price ;
	}
	public String getInfo(){
		return "ͼ������:" + title + "����:" + price ;
	}
	public boolean compare(Book book){
		if(this == book){
			return true ;
		}
		if(book == null){
			return false ;
		}
		if(this.title.euqals(book.title) && this.price == book.price){
			return true ;
		}
		return false ;
	}
}
class Link{
	//================�ڲ���=================
	private class Node{
		private Book data ;
		private Node next ;
		public Node(Book data){
			this.data = data ;
		} 
		public void addNode(Node newNode){
			if(this.next == null){
				this.next = newNode ;
			}else{
				this.next.addNode(newNode) ;
			}
		}
		public boolean containsNode(Book data){
			if(data.compare(this.data)){
				return true ;
			}else{
				if(this.next != null){
					return this.next.containsNode(data) ;
				}else{
					return false ;
				}
			}
		}
		public Book getNode(int index){
			if(Link.this.foot ++ == index){
				return this.data ;
			}else{
				return this.next.getNode(index) ;
			}
		}
		public void setNode(int index,Book data){
			if(Link.this.foot ++ == index){
				this.data = data ;
			}else{
				this.next.setNode(index,data) ;
			}
		}
		public void removeNode(Node previous,Book data){
			 if(data.compare(this.data)){
				previous.next = this.next ;
			 }else{
				this.next.removeNode(this,data) ;
			 }
		}
		public void toArrayNode(){
			Link.this.retArray[Link.this.foot++] = this.data ;
			if(this.next != null){
				this.next.toArrayNode() ;
			}
		}
	}
	//================�ڲ���=================
	private Node root ;
	private int count ;
	private int foot ;
	private Book retArray[] ;
	public void add(Book data){   //��������
		if(data == null){   //������������������Ϊ��
			return ;
		}
		Node newNode = new Node(data) ;
		if(this.root == null){
			this.root = newNode ;
		}else{
			this.root.addNode(newNode) ;
		}
		this.count++ ;
	}
	public int size(){   //ȡ��������Ԫ�ظ���
		return this.count ;
	}
	public boolean isEmpty(){   //�ж������Ƿ�Ϊ��
		return this.count == 0 ;
	}
	public boolean contains(Book data){   //���ݲ�ѯ
		if(data == null || this.root == null){
			return false ;
		}else{
			return this.root.containsNode(data) ;
		}
	}
	public Book get(int index){   //��������ȡ������
		if(index > this.count -1){
			return null ;
		}
		this.foot = 0 ;
		return this.root.getNode(index) ;
	}
	public void set(int index,Book data){  //������������������������������
		if(index > count -1){
			return ;
		}
		this.foot = 0 ;
		this.root.setNode(index,data) ;
	}
	public void remove(Book data){   //ɾ������
		if(this.contains(data)){
			if(data.compare(this.root.data)){
				this.root = this.root.next ;
			}else{
				this.root.next.removeNode(this.root,data) ;
			}
		}
	}
	public Book []toArray(){   //������ת��Ϊ����
		if(this.root == null){
			return null ;
		}
		this.foot = 0 ;
		this.retArray = new Book[this.count] ;
		this.root.toArrayNode() ;
		return this.retArray ;
	}	
}
 
public class LinkDemo{
	public static void main(String args[]){
		Link link = new Link() ;
		link.add(new Book("Java",11.1)) ;
		link.add(new Book("Oralce",22.2)) ;
		link.add(new Book(null,0.0)) ;
		Book books[] = link.toArray() ;
 		for(int x = 0; x < books.length; x++){
			System.out.println(books[x].getInfo()) ;
		}
	}
}