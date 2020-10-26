* 		public class CodeRule { 
* 		    private static int i = 1; 
* 		    public static void main(String[] args) { 
* 		        if (i > 0) { 
* 		            System.out.println(i); 
* 		        } 
* 		        if (i > 0 && i < 2) { 
* 		            System.out.println(i); 
* 		        }
* 		        if (i > 0 && i < 2) { 
* 		            System.out.println(i); 
* 		        } else if (i > 2) { 
* 		            System.out.println(i + 1); 
* 		        } else { 
* 		            System.out.println(i); 
* 		        } 
* 		        
* 		         if (i == 1) { 
* 		             System.out.println(i); 
* 		         } 
* 		         else { 
* 		             System.out.println(i); 
* 		         } 
* 		          
* 		         while (i > 0 && i < 2) { 
* 		             System.out.println(i); 
* 		             i++; 
* 		         } 
* 		          
* 		         for (int j = 0; j < 10; j++) { 
* 		             System.out.println(i); 
* 		         } 
* 		         
* 		         for (int j=0; j<10; j++) { 
* 		             System.out.println(i); 
* 		         } 
* 		          
* 		  
* 		         int a = 1 + 2; 
* 		         int b = 1 - 2; 
* 		         int c = 1 * 2; 
* 		         int d = 1 / 2; 
* 		          
* 		         
* 		         int j = i > 2 ? 1 : -1; 
* 		          
* 		          
* 		         sum(a, b); 
* 		         sum(c + d, j); 
* 		    } 
* 		     
* 		    
* 		    private static int sum(int i, int j) { 
* 		        return i + j; 
* 		    } 
* 		     
* 		 
* 		}
