# Farm_SupplyChain_Management
Decentralized Farm Supply Chain System to ensure transparency and quick traceability for Farm Supply chains


This Project for a smart contract that implements a supply chain management system for a farm. The code consists of two files: Product.java and FarmSupplyChain.java.

Product.java defines a class called Product, which represents a product in the supply chain. The class has several properties, including the product ID, a description
of the product, the name and address of the producer, the date the product was harvested, the name and address of the distributor, the date the product was sold from 
the producer to the distributor, the name and address of the retailer, and the date the product was sold from the distributor to the retailer. The class also includes g
etter methods for these properties, as well as an overridden equals() method to check for equality between two Product objects, an overridden hashCode() method to 
calculate a hash code for the object, and an overridden toString() method to return a string representation of the object.

FarmSupplyChain.java is the main contract file. It defines a class called FarmSupplyChain that implements the ContractInterface interface. The contract has several 
methods annotated with @Transaction, which are called by clients to interact with the contract. The methods include creating a new Product, retrieving a Product by 
ID, and updating the details of a Product. The FarmSupplyChain class also includes a main() method for testing the contract.

The FarmSupplyChain contract uses the ChaincodeStub class from the Hyperledger Fabric SDK to interact with the blockchain network. It also uses the Genson library 
to serialize and deserialize JSON data
