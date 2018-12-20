class ArrayUtils {
  // adapted from https://stackoverflow.com/questions/5306680/move-an-array-element-from-one-array-position-to-another
  static moveElement (arr, oldIndex, newIndex) {
    if (newIndex >= arr.length) {
      var k = newIndex - arr.length + 1
      while (k--) {
        arr.push(undefined)
      }
    }
    arr.splice(newIndex, 0, arr.splice(oldIndex, 1)[0])
    return arr

  }
}

export default ArrayUtils
