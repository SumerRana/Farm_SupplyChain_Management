package FarmSupplyChain;

import com.owlike.genson.annotation.JsonProperty;
import org.hyperledger.fabric.contract.annotation.DataType;
import org.hyperledger.fabric.contract.annotation.Property;
import java.util.Objects;

@DataType()
public final class Product {

	@Property()
	private final String id;

	@Property()
	private final String productDescription;

	@Property()
	private final String producerName;

	@Property()
	private final String producerAddress;

	@Property()
	private final String harvestDate;

	@Property()
	private final String distributerName;

	@Property()
	private final String distributerAddress;

	@Property()
	private final String producerToDistributerDate;

	@Property()
	private final String retailerName;

	@Property()
	private final String retailerAddress;

	@Property()
	private final String distributerToRetailerDate;

	public String getId() {
		return id;
	}

	public String getProductDescription() {
		return productDescription;
	}

	public String getProducerName() {
		return producerName;
	}

	public String getProducerAddress() {
		return producerAddress;
	}

	public String getHarvestDate() {
		return harvestDate;
	}

	public String getDistributerName() {
		return distributerName;
	}

	public String getDistributerAddress() {
		return distributerAddress;
	}

	public String getProducerToDistributerDate() {
		return producerToDistributerDate;
	}

	public String getRetailerName() {
		return retailerName;
	}

	public String getRetailerAddress() {
		return retailerAddress;
	}

	public String getDistributerToRetailerDate() {
		return distributerToRetailerDate;
	}

	public Product(@JsonProperty("id") final String id,
			@JsonProperty("productDescription") final String productDescription,
			@JsonProperty("producerName") final String producerName,
			@JsonProperty("producerAddress") final String producerAddress,
			@JsonProperty("harvestDate") final String harvestDate,
			@JsonProperty("distributerName") final String distributerName,
			@JsonProperty("distributerAddress") final String distributerAddress,
			@JsonProperty("producerToDistributerDate") final String producerToDistributerDate,
			@JsonProperty("retailerName") final String retailerName,
			@JsonProperty("retailerAddress") final String retailerAddress,
			@JsonProperty("distributerToRetailerDate") final String distributerToRetailerDate) {

		this.id = id;
		this.productDescription = productDescription;
		this.producerName = producerName;
		this.producerAddress = producerAddress;
		this.harvestDate = harvestDate;
		this.distributerName = distributerName;
		this.distributerAddress = distributerAddress;
		this.producerToDistributerDate = producerToDistributerDate;
		this.retailerName = retailerName;
		this.retailerAddress = retailerAddress;
		this.distributerToRetailerDate = distributerToRetailerDate;
	}

	@Override
	public boolean equals(final Object obj) {
		if (this == obj) {
			return true;
		}

		if ((obj == null) || (getClass() != obj.getClass())) {
			return false;
		}

		Product other = (Product) obj;

		return Objects.deepEquals(new String[] { getId(), getProductDescription(), getProducerName(),
				getProducerAddress(), getHarvestDate(), getDistributerName(), getDistributerAddress(),
				getProducerToDistributerDate(), getRetailerName(), getRetailerAddress(),
				getDistributerToRetailerDate() },

				new String[] { other.getId(), other.getProductDescription(), other.getProducerName(),
						other.getProducerAddress(), other.getHarvestDate(), other.getDistributerName(),
						other.getDistributerAddress(), other.getProducerToDistributerDate(),
						other.getRetailerName(), other.getRetailerAddress(), other.getDistributerToRetailerDate() });
	}

	@Override
	public int hashCode() {
		return Objects.hash(getId(), getProductDescription(), getProducerName(), getProducerAddress(),
				getHarvestDate(), getDistributerName(), getDistributerAddress(), getProducerToDistributerDate(),
				getRetailerName(), getRetailerAddress(), getDistributerToRetailerDate());
	}

	@Override
	public String toString() {
		return this.getClass().getSimpleName() + "@" + Integer.toHexString(hashCode()) + " [id=" + id
				+ ", productDescription=" + productDescription + ", producerName=" + producerName
				+ ", producerAddress=" + producerAddress + ", harvestDate=" + harvestDate
				+ ", distributerName=" + distributerName + ", distributerAddress=" + distributerAddress
				+ ", producerToDistributerDate=" + producerToDistributerDate + ", retailerName=" + retailerName
				+ ", retailerAddress=" + retailerAddress + ", distributerToRetailerDate=" + distributerToRetailerDate
				+ "]";
	}

}