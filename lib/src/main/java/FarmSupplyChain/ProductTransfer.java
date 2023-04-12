package FarmSupplyChain;

import org.hyperledger.fabric.contract.Context;
import org.hyperledger.fabric.contract.ContractInterface;
import org.hyperledger.fabric.contract.annotation.Contract;
import org.hyperledger.fabric.contract.annotation.Default;
import org.hyperledger.fabric.contract.annotation.Info;
import org.hyperledger.fabric.contract.annotation.Transaction;
import org.hyperledger.fabric.shim.ChaincodeException;
import org.hyperledger.fabric.shim.ChaincodeStub;
import com.owlike.genson.Genson;

@Contract(name = "FarmSupplyChain", info = @Info(title = "FarmSupplyChain contract", description = "A Sample Car transfer chaincode example", version = "0.0.1-SNAPSHOT"))

@Default
public final class ProductTransfer implements ContractInterface {

	private final Genson genson = new Genson();

	private enum FarmSupplyChainErrors {
		Product_NOT_FOUND,
		Product_ALREADY_EXISTS
	}

	/**
	 * Add some initial properties to the ledger
	 *
	 * @param ctx the transaction context
	 */
	@Transaction()
	public void initLedger(final Context ctx) {

		// ChaincodeStub stub= ctx.getStub();

		// Car Car = new Car("1", "Maruti","Mark","6756");

		// String CarState = genson.serialize(Car);

		// stub.putStringState("1", CarState);
	}

	/**
	 * This function is used to add a new product to the ledger. This function is
	 * called by the
	 * producer or farmer by using the below parameters.
	 * Input Parameters:
	 * 
	 * @param ctx             the transaction context
	 * @param id              the product id of the farm product
	 * @param description     the description of the farm product
	 * @param producerName    producer or farmer name
	 * @param producerAddress producer or farmer address
	 * @param harvestdate     harvestdate of the farm product
	 * @return the Product details
	 * 
	 *         This function also checks if:
	 *         ● The same asset with the same product ID does not exist already
	 **/

	@Transaction()
	public Product addNewProduct(final Context ctx,
			final String _id,
			final String _description,
			final String _producerName,
			final String _producerAddress,
			final String _harvestDate) {

		ChaincodeStub stub = ctx.getStub();

		String ProductState = stub.getStringState(_id);

		if (!ProductState.isEmpty()) {
			String errorMessage = String.format("Product %s already registered", _id);
			System.out.println(errorMessage);
			throw new ChaincodeException(errorMessage, FarmSupplyChainErrors.Product_ALREADY_EXISTS.toString());
		}

		Product product = new Product(_id, _description, _producerName, _producerAddress, _harvestDate, "", "", "", "",
				"", "");

		ProductState = genson.serialize(product);

		stub.putStringState(_id, ProductState);

		return product;
	}

	/**
	 * Transfer the asset to the distributer from the producer
	 * This function helps to transfer the farm product from a producer (farmer) to
	 * a distributor.
	 * 
	 * @param ctx                the transaction context
	 * @param id                 product Id of the Farm Product
	 * @param distributerName    distributer name
	 * @param distributerAddress distributer address
	 * @param transferDate       transaction date between distributer and producer
	 * @return the product id
	 * 
	 *         This function also does the following check:
	 *         ● An asset should be present in the ledger
	 * 
	 */

	@Transaction()
	public Product transferProducerToDistributor(final Context ctx,
			final String _id,
			final String _distributerName,
			final String _distributerAddress,
			final String _producerToDistributerDate) {
		ChaincodeStub stub = ctx.getStub();

		String ProductState = stub.getStringState(_id);

		if (ProductState.isEmpty()) {
			String errorMessage = String.format("Product %s not Registered", _id);
			System.out.println(errorMessage);
			throw new ChaincodeException(errorMessage, FarmSupplyChainErrors.Product_NOT_FOUND.toString());
		}

		Product product = genson.deserialize(ProductState, Product.class);

		Product newProduct = new Product(product.getId(),
				product.getProductDescription(),
				product.getProducerName(),
				product.getProducerAddress(),
				product.getHarvestDate(),
				_distributerName,
				_distributerAddress,
				_producerToDistributerDate,
				product.getRetailerName(),
				product.getRetailerAddress(),
				product.getDistributerToRetailerDate());

		String newProductState = genson.serialize(newProduct);

		stub.putStringState(_id, newProductState);

		return newProduct;
	}

	/**
	 * o transfer the farm product Ownership to the retailer from the distributor.
	 * 
	 * @param ctx             the transaction context
	 * @param id              product Id of the Farm Product
	 * @param retailerName    retailer name
	 * @param retailerAddress retailer address
	 * @param transferDate    transaction date between distributer and retailer
	 * @return the product id
	 * 
	 *         This function also does the following check:
	 *         .
	 *         ● An asset should be present in the ledger
	 */

	@Transaction()
	public Product transferDistributorToRetailer(final Context ctx,
			final String _id,
			final String _retailerName,
			final String _retailerAddress,
			final String _distributerToRetailerDate) {
		ChaincodeStub stub = ctx.getStub();

		String ProductState = stub.getStringState(_id);

		if (ProductState.isEmpty()) {
			String errorMessage = String.format("Product %s not Registered", _id);
			System.out.println(errorMessage);
			throw new ChaincodeException(errorMessage, FarmSupplyChainErrors.Product_NOT_FOUND.toString());
		}

		Product product = genson.deserialize(ProductState, Product.class);

		Product newProduct = new Product(product.getId(),
				product.getProductDescription(),
				product.getProducerName(),
				product.getProducerAddress(),
				product.getHarvestDate(),
				product.getDistributerName(),
				product.getDistributerAddress(),
				product.getProducerToDistributerDate(),
				_retailerName,
				_retailerAddress,
				_distributerToRetailerDate);

		String newProductState = genson.serialize(newProduct);

		stub.putStringState(_id, newProductState);

		return newProduct;
	}

	/**
	 * View asset details from the ledger
	 * This function helps to retrieve asset product details from the ledger.
	 * Input Parameters
	 * 
	 * @param ctx the transaction context
	 * @param id  product Id of the farm Product
	 * @return Farm Product supply chain details
	 */

	@Transaction()
	public Product queryProductById(final Context ctx, final String _id) {
		ChaincodeStub stub = ctx.getStub();
		String ProductState = stub.getStringState(_id);

		if (ProductState.isEmpty()) {
			String errorMessage = String.format("Product %s not Registered", _id);
			System.out.println(errorMessage);
			throw new ChaincodeException(errorMessage, FarmSupplyChainErrors.Product_NOT_FOUND.toString());
		}

		Product product = genson.deserialize(ProductState, Product.class);
		return product;
	}
}
