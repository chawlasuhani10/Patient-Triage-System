# Patient-Triage-System
For this project, implemented a futuristic automated emergency room (ER) triage system.
System will receive data in the form of patient and injury pairs over time and automatically
assign patients to doctors as the resources become available depending on the severity of the
patient’s injury. 
To accomplish this, made use of a data structure called a priority queue – more
specifically, a heap-based priority queue.The priority is on the patient’s condition, which has these levels in increasing order of priority: LIGHT, MILD, SEVERE,
CRITICAL.

### Code Structure:

#### The Heap.java file: 
contains the definition of a Heap class. First, it uses Java generics to make the data structure code be valid regardless of which type of elements are
stored in it, i.e a Heap<int> stores integer values, a Heap<Patient> stores patient objects.
Second, the Heap accepts a comparator object to define the order (priority) of its elements.
Finally, the Heap accepts the boolean flag isMaxHeap to define if the instance will be a max
heap, i.e highest priority first, or a min heap, i.e lowest priority first.

#### Comparator Interface: 
The program makes use of the Java Comparator interface. Like the Comparable interface, it
allows data to be compared and thus ordered. Comparator is more flexible as the way data can
be compared can be implemented by different Comparator classes. These are the two
Comparators used in this program: 

#### PatientAgeComparator.java
The compare method performs the comparison between the age attribute of patients pt1 and
pt2. The age of a patient is an integer.

#### PatientConditionComparator.java
The compare method for this Comparator performs the comparison between the condition
attribute of patients pt1 and pt2. 
