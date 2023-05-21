package luckytree.poom.core.enums

import luckytree.poom.core.exceptions.NotFoundException

enum class MemberStatus {
    NORMAL,

    DORMANT,

    LEAVE,
    ;

    fun isAlreadyDeleted() {
        if (this == LEAVE) throw NotFoundException()
    }
}
