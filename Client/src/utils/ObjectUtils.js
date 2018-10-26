export default function DeepCloneObject(source) {
    let key, value;
    let clone = Object.create(source);

    for (key in source) {
        if (source.hasOwnProperty(key) === true) {
            value = source[key];

            if (value !== null && typeof value === "object") {
                clone[key] = DeepCloneObject(value);
            } else {
                clone[key] = value;
            }
        }
    }
    return clone;
}


