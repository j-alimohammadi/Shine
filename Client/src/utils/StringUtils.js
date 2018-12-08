class StringUtils {
    /**
     * Check weather parameter str is defined and has length >0
     * @param str
     */
    static isNotBlank(str) {
        // is type of str string?
        if ((typeof str != 'undefined' && str) && typeof str.valueOf() == 'string') {
            str = str.trim();
            return str.length > 0;
        }
    }

}

export default StringUtils