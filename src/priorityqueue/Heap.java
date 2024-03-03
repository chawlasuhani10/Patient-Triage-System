package priorityqueue;

import java.util.Comparator;
import java.util.PriorityQueue;

public class Heap<T> implements PriorityQueueADT<T> {

  private int numElements;
  private T[] heap;
  private boolean isMaxHeap;
  private Comparator<T> comparator;
  private final static int INIT_SIZE = 5;


 
  /**
   * Constructor for the heap.
   * @param comparator comparator object to define a sorting order for the heap elements.
   * @param isMaxHeap Flag to set if the heap should be a max heap or a min heap.
   */
  public Heap(Comparator<T> comparator, boolean isMaxHeap) {
      //TODO: Implement this method.
      this.comparator = comparator;
      this.isMaxHeap = isMaxHeap;
      heap = (T[]) new Object[INIT_SIZE];
   
      
  }

  // HELPER METHODS 

  private void swap(int index, int parentIndex) {
    T temp  = heap[parentIndex];
    heap[parentIndex] = heap[index];
    heap[index] = temp; 
  }

  private int getLeftChildOf(int parentIndex){
    return 2*parentIndex +1;
  }

  private int getRightChildOf(int parentIndex){
    return 2*parentIndex +2;
  }

  private int getParentOf(int childIndex){
    return (childIndex-1)/2;
  }



  /**
   * This results in the entry at the specified index "bubbling up" to a location
   * such that the property of the heap are maintained. This method should run in
   * O(log(size)) time.
   * Note: When enqueue is called, an entry is placed at the next available index in 
   * the array and then this method is called on that index. 
   *
   * @param index the index to bubble up
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleUp(int index) {
      //TODO: Implement this method.
      //first need to check if its a max heap or a min heap 
      if( index < 0 || index >= getSize()){
        throw new IndexOutOfBoundsException();
      }
       
     if(index == 0){
        return;
     }

      if(isMaxHeap) {  //means its a Max Heap
        if(comparator.compare(heap[index], heap[getParentOf(index)]) > 0) {    //means currIndex value is greater than its parent
          swap(index, getParentOf(index)); 
          bubbleUp((getParentOf(index)));
        }
        else{
          return;
        }
      }

      else{  // means its  a Min Hep 
        if(comparator.compare(heap[index], heap[getParentOf(index)]) < 0) {  //means currIndex value is less than its parent
          swap(index, (getParentOf(index))); 
          bubbleUp((getParentOf(index)));
        }
        else{
          return;
        }
      }
         
  }

  

  /**
   * This method results in the entry at the specified index "bubbling down" to a
   * location such that the property of the heap are maintained. This method
   * should run in O(log(size)) time.
   * Note: When remove is called, if there are elements remaining in this
   *  the bottom most element of the heap is placed at
   * the 0th index and bubbleDown(0) is called.
   * 
   * @param index
   * @throws IndexOutOfBoundsException if invalid index
   */
  public void bubbleDown(int index) {
      //TODO: Implement this method.
      if( index < 0 || index >= heap.length){
        throw new IndexOutOfBoundsException();
      }

      
      if(index >= getSize()/2) {
        return;
      }
      //if (getLeftChildOf(index) < heap.length && getRightChildOf(index) < heap.length) {
       // if(heap[getLeftChildOf(index)] == null && heap[getRightChildOf(index)] == null){
        //  return;
        //}
      // } 
     

      if(isMaxHeap) {   //means its a Max Heap

        //condition when the index has only one child 
        if(heap[getLeftChildOf(index)] == null){
          if(comparator.compare(heap[index], heap[getRightChildOf(index)]) < 0){
            swap(index, getRightChildOf(index));
            
          }
          return;
        }

        if(heap[getRightChildOf(index)] == null){
          if(comparator.compare(heap[index], heap[getLeftChildOf(index)]) < 0){
            swap(index, getLeftChildOf(index));
            
          }
          return;
        }


        if(comparator.compare(heap[index], heap[getLeftChildOf(index)]) < 0  ||  comparator.compare(heap[index], heap[getRightChildOf(index)]) < 0) {  // if the parent- currIndex is smaller than any of its children
          if(comparator.compare(heap[getLeftChildOf(index)], heap[getRightChildOf(index)]) >0 ){
              swap(index, getLeftChildOf(index)); 
              bubbleDown(getLeftChildOf(index));
        }
          else{
            swap(index, getRightChildOf(index)); 
            bubbleDown(getRightChildOf(index));
          }
        }
        
        else{
          return;
        }
      
    } 

      else{  // means its  a Min Hep 

        //if the index has only one child 
        if(heap[getLeftChildOf(index)] == null){
          if(comparator.compare(heap[index], heap[getRightChildOf(index)]) > 0){ //index is greater than its child in a heap. 
            swap(index, getRightChildOf(index));
           
          }
          return;
        }

        if(heap[getRightChildOf(index)] == null){
          if(comparator.compare(heap[index], heap[getLeftChildOf(index)]) > 0){
            swap(index, getLeftChildOf(index));
            
          }
          return;
        }

        //if the currIndex value is larger than any of its children
        if(comparator.compare(heap[index], heap[getLeftChildOf(index)]) > 0  ||  comparator.compare(heap[index], heap[getRightChildOf(index)]) > 0) {
          if(comparator.compare(heap[index], heap[getLeftChildOf(index)]) > 0){
              swap(index, getLeftChildOf(index)); 
              bubbleDown(getLeftChildOf(index));
        }
          if(comparator.compare(heap[index], heap[getRightChildOf(index)]) > 0){
            swap(index, getRightChildOf(index)); 
            bubbleDown(getRightChildOf(index));
          }
        }
        else{
          return;
        }
      }
      
  }
         

  /**
   * Test for if the queue is empty.
   * @return true if queue is empty, false otherwise.
   */
  public boolean isEmpty() {
    boolean isEmpty = false;
      //TODO: Implement this method.
      if(numElements == 0){
        isEmpty = true;
      }
    return isEmpty;
  }

  /**
   * Number of data elements in the queue.
   * @return the size
   */
  public int getSize(){
    int size = -100;
      //TODO: Implement this method.
      size = numElements;
        return size;
  }

  /**
   * Compare method to implement max/min heap behavior. It changes the value of a variable, compareSign, 
   * based on the state of the boolean variable isMaxHeap. It then calls the compare method from the 
   * comparator object and multiplies its output by compareSign.
   * @param element1 first element to be compared
   * @param element2 second element to be compared
   * @return positive int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * negative int otherwise (if isMaxHeap),
   * return negative int if {@code element1 > element2}, 0 if {@code element1 == element2}, 
   * positive int otherwise (if ! isMinHeap).
   */
  public int compareElements(T element1 , T element2) {
    int result = 0;
    int compareSign =  -1;
    if (isMaxHeap) {
      compareSign = 1;
    }
    result = compareSign * comparator.compare(element1, element2);
    return result;
  }

  /**
   * Return the element with highest (or lowest if min heap) priority in the heap 
   * without removing the element.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T peek() throws QueueUnderflowException {
     T data = null;
      //TODO: Implement this method.
      if(this.isEmpty()){
        throw new QueueUnderflowException();
      }
      data = heap[0];

    return data;
  }  

  /**
   * Removes and returns the element with highest (or lowest if min heap) priority in the heap.
   * @return T, the top element
   * @throws QueueUnderflowException if empty
   */
  public T dequeueElement() throws QueueUnderflowException{
    T data = null;
      //TODO: Implement this method.
      if(this.isEmpty()){
        throw new QueueUnderflowException();
      }
    
      data = heap[0];
      T replaceElement = heap[numElements-1];
      heap[0] = replaceElement;
      heap[numElements-1] = null;
      --numElements;
      bubbleDown(0);
      
    return data;
  }

  /**
   * Enqueue the element.
   * @param the new element
   */
  public void enqueueElement(T newElement) {
      //TODO: Implement this method.
    
    if(this.isEmpty()) {
      heap[0] = newElement;
      ++numElements;
      return;
    }

    int availableIndex = this.getSize();
    if(availableIndex < heap.length) {
      heap[availableIndex] = newElement;
      ++numElements;
      bubbleUp(availableIndex);
      return;
    }

    if(availableIndex == heap.length){
      expandCapacity();
      heap[availableIndex] = newElement;
      ++numElements;
      bubbleUp(availableIndex);
      return;
    }

  }

  private void expandCapacity(){
    T[] expandedHeap = (T[]) new Object[2*heap.length];
    for(int i = 0; i < heap.length; i++){
      expandedHeap[i] = heap[i];
      }
    heap = expandedHeap; 
  }



}

// Project doubts- how to intitalize the array, how to run the tests
// ask hannah- recursive solution for bubble Up bubble Down or Zybook solution
// enqueue element- how to find the available position for insertion and how to expand the array (array of generic type not being created)

// Have to initialize the array in the constructor 

// check for IsEmpty cases
//if the index being accessed is out of the array 
// check if it has reached the root
// or maybe reached a leaf node 

// to find the end of the array use the getSize method and then the available index is one plus that 