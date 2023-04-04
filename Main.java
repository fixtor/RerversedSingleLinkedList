import java.util.Iterator;

public class Main {
	public static void main(String[] args) {
		SingleLinkedList<Digits> digitList = new SingleLinkedList<>();
		//заполняем односвязный список
		digitList.addEnd(new Digits(15));
		digitList.addEnd(new Digits(20));
		digitList.addEnd(new Digits(11));
		digitList.addEnd(new Digits(10));

		//Распечатаем односвязный список в порядке заложенным в список
		for(Digits digits: digitList) {
			System.out.println(digits);
		}

		digitList.reverse();
		System.out.println("*************reversed*************");

		//Распечатаем перевернутый односвязный список
		for(Digits digits: digitList) {
			System.out.println(digits);
		}
	}

	//Числовые элементы списка
	public static class Digits{
		int digit;

		public Digits(int digit) {
			this.digit = digit;
		}
		@Override
		public String toString() {
			return "" + digit;
		}
	}

	// Создаем односвязный список с возможностью вставки и проходу по элементам
	public static class SingleLinkedList<T> implements Iterable<T> {
		ListItem<T> head;
		ListItem<T> tail;

		@Override
		public Iterator<T> iterator() {
			return new Iterator<T>() {
				@Override
				public boolean hasNext() {
					return current != null;
				}
				@Override
				public T next() {
					T data = current.data;
					current = current.next;
					return data;
				}
				ListItem<T> current = head;
			};
		}

		private static class ListItem<T>{

			T data;
			ListItem<T> next;
		}

		public boolean isEmpty(){
			return head == null;
		}
		public void addEnd(T item){
			ListItem<T> newItem = new ListItem<>();
			newItem.data = item;
			if (isEmpty()){
				head = newItem;
				tail = newItem;
			} else {
				tail.next = newItem;
				tail = newItem;
			}

		}

		/* Метод разворота односвязного списка
		next - ссылка на следующий элемент
		head - ссылка пердыдущий
		current текущий
		У текущего элемента меняем указатель на предыдущий и меняем дальше все указатели */
		public void reverse(){
			if (!isEmpty() && head.next != null){
				tail = head;
				ListItem<T> current = head.next;
				head.next = null;
				while (current != null) {
					ListItem<T> next = current.next;
					current.next = head;
					head = current;
					current = next;
				}
			}
		}
	}
}
