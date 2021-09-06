
public class BinarySearch {
    
    public Student findOneByName(Student[] students, String name) {     
        int l=0;
        int r=students.length;
        return this.findOneByName(students, l, r, name);
    }
    
    public Student findOneByAge(Student[] students, int age) {
        int l=0;
        int r=students.length;
        return this.findOneByAge(students, l, r, age);
    }
    
    public Student findOneByScore(Student[] students, float score) {
        int l=0;
        int r=students.length;
        return this.findOneByScore(students, l, r, score);
    }
    
    private Student findOneByAge(Student[] students, int l, int r, int age){
        
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the 
            // middle itself
            if (students[mid].getAge() == age)
               return students[mid];
 
            // If element is smaller than mid, then 
            // it can only be present in left subarray
            if (students[mid].getAge() > age)
               return findOneByAge(students, l, mid-1, age);
 
            // Else the element can only be present
            // in right subarray
            return findOneByAge(students, mid+1, r, age);
        }
 
        // We reach here when element is not present
        //  in array
        return null;
    }

    private Student findOneByScore(Student[] students, int l, int r, float score){
        
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the 
            // middle itself
            if (students[mid].getScore() == score)
               return students[mid];
 
            // If element is smaller than mid, then 
            // it can only be present in left subarray
            if (students[mid].getScore() > score)
               return findOneByScore(students, l, mid-1, score);
 
            // Else the element can only be present
            // in right subarray
            return findOneByScore(students, mid+1, r, score);
        }
 
        // We reach here when element is not present
        //  in array
        return null;
    }

    private Student findOneByName(Student[] students, int l, int r, String name){
        
        if (r>=l)
        {
            int mid = l + (r - l)/2;
 
            // If the element is present at the 
            // middle itself
            if (students[mid].getName().compareTo(name) == 0)
               return students[mid];
 
            // If element is smaller than mid, then 
            // it can only be present in left subarray
            if (students[mid].getName().compareTo(name) > 0)
               return findOneByName(students, l, mid-1, name);
 
            // Else the element can only be present
            // in right subarray
            return findOneByName(students, mid+1, r, name);
        }
 
        // We reach here when element is not present
        //  in array
        return null;
    }
    
}
