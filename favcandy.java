/*CSCE 111 Section 502
GUI Calculator Method: Should you drive
This calculator collects information from you and determines whether or not
you should drive right now. This is not a calculator that should be used outside
of this class and is created PURELY for me to understand class concepts.
Name: Liliana Hildebrand
UIN: 930006956
Additional Source: https://www.geeksforgeeks.org/path-relativize-method-in-java-with-examples/
*/
public class favcandy {
private int Taste;// this is the emotional state they are in at the moment
private  double PersonAge;
public void  setTaste( int flavor){
  Taste = flavor;
}
public void setAge(double age){
 PersonAge = age;
}

public String favcandy(){
if (Taste >6){
  if(PersonAge < 0){
    return "You're not real";
  }// if someone put in a negative Number
  else if (PersonAge>=60){
    return "Laffy Taffy";
  }
  else if (PersonAge>=50){
    return "Sour Patch Kids";
  }
  else if (PersonAge >=40){
    return "Air Heads";
  }
  else if (PersonAge >= 30){
    return "Nerds Rope";
  }
  else if (PersonAge >= 20){
    return "Reese's Peanut Butter Cups";
  }
  else if (PersonAge >= 10){
    return "Fruit Roll Up";
  }
  else if (PersonAge >=0){
    return "Skittles";
  }


}// if they are more in the sweet side of sour
else{
  if(PersonAge < 0){
    return "You're not real";
  }// if someone put in a negative Number
  else if (PersonAge>=60){
    return "Candy corn";
  }
  else if (PersonAge>=50){
    return "Circus peanuts";
  }
  else if (PersonAge >=40){
    return "Peanut butter Kisses";
  }
  else if (PersonAge >= 30){
    return "Smarties";
  }
  else if (PersonAge >= 20){
    return "Necco wafers";
  }
  else if (PersonAge >= 10){
    return "Wax coke bottles";
  }
  else if (PersonAge >=0){
    return "Black licorice";
  }
  else{}


}// if they are more on the bitter side
return "";
}// end of method
}// end of class
