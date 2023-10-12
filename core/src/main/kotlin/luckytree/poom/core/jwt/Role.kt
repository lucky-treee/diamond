package luckytree.poom.core.jwt

enum class Role(val role: String) {
    ADMIN("ROLE_ADMIN"),

    MEMBER("ROLE_MEMBER");

    companion object {
        @JvmStatic
        fun from(role: String) = Role.values().first() { it.role == role }
    }
}
