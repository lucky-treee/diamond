package luckytree.poom.core.exceptions


class NotFoundException(
    message: String = "can't find the resource.",
) : RuntimeException(message)
