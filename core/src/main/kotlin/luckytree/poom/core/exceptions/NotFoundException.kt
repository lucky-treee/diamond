package luckytree.poom.core.exceptions


class NotFoundException : RuntimeException {
    override var message: String
    var data: Any?

    constructor(message: String) {
        this.message = message
        data = null
    }

    constructor(message: String, data: Any?) {
        this.message = message
        this.data = data
    }
}
