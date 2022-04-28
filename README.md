# Virtual-Scanner

Simulation software which works on commands from the interactive software. The software mocks the smart conveyor belt system used for diversion and efficient workload management in warehouses.

<h1>Algorithm and Concept</h1>
<ul>
  <li>
    The algorithm aims at constant flow of products thus aiming highest efficiency and work management. The software waits till the first product reaches and interacts with the first scanner. Reagardless of the output from the scanner, the next product in queue starts on the conveyor belt. This ensures minimum idle time for any scanner. 
  </li>
  <li>
    The database management for the products in line, has a garbage collector which is efficiently based on the HashMap utility of Java. The products which reach the final destination are removed from the database storing the products in line.
  </li>
  <li>
    The interaction software mimics the OES softwares which are primarily used for conveyor belt management. The software gives two path choosing decisions- '1' implying to direct the product to the scanner, or '999' directing to let the product follow the initial path.
    The software interacts via JSON formatted I/O. 
    The decisions are genrally based on network flow analysis, but in stimulation are based on a simple random number concept.
  </li>
  </ul>
    
<h1>Usage</h1>
<ul>
  <li>The user (warehouse operator) needs to provide the unique barcode id for each product in file format. </li>
  <li>Sequence of the scanning hardware needs to be provided. </li>
  </ul>
 
 <br>
 <h1>Output</h1>
 <ol>
  <li><h3>Output file : </h3>
    <br>
    This contains the interaction of each of the product with the scanner. The JSON formmated file contains information about the product, the scanner and the command it recieved. This is the file recieved at the user end and the commands from the interaction software.
  </li>
  <li><h3> Scanner log : </h3>
    <br>
    This file contains the responses of the scanners (simulation software) to the interactive software.
  </li>
  <li><h3> Destination log : </h3>
    <br>
    This file contains the information regarding the final handling of the product by the scanners. 
  </li>
 </ol>
 
 
      
