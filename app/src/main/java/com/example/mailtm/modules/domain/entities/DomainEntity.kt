import com.google.gson.annotations.SerializedName

data class DomainEntity (
    @SerializedName("@context")
    var _context: String,

    @SerializedName("@id")
    var _id: String,

    @SerializedName("@type")
    var _type: String,

    @SerializedName("hydra:totalItems")
    var totalItems: Int,

    @SerializedName("hydra:member")
    var members: List<DomainMember>
)

data class DomainMember(
    @SerializedName("@id")
    var _id: String,

    @SerializedName("@type")
    var _type: String,

    @SerializedName("id")
    var id: String,

    @SerializedName("domain")
    var domain: String,

    @SerializedName("isActive")
    var isActive: Boolean,

    @SerializedName("isPrivate")
    var isPrivate: Boolean,

    @SerializedName("createdAt")
    var createdAt: String,

    @SerializedName("updatedAt")
    var updatedAt: String
)
