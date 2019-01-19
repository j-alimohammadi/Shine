class ShineHttpClient {
  static postData (url, dataObject) {

    if (!dataObject) {
      dataObject = []
    }
    return fetch(url, {
      body: JSON.stringify(dataObject),
      cache: 'no-cache',
      headers: {
        'content-type': 'application/json'
      },
      method: 'POST',
      mode: 'cors',
      redirect: 'follow',
      referrer: 'no-referrer',
    })
  }

  static putData (url, dataObject) {
    if (!dataObject) {
      dataObject = []
    }
    return fetch(url, {
      body: JSON.stringify(dataObject),
      cache: 'no-cache',
      headers: {
        'content-type': 'application/json'
      },
      method: 'PUT',
      mode: 'cors',
      redirect: 'follow',
      referrer: 'no-referrer',
    })

  }

  static getData (url) {
    return fetch(url, {
      cache: 'no-cache',
      method: 'GET',
      mode: 'cors',
      redirect: 'follow',
      referrer: 'no-referrer',
    })

  }

  static deleteData (url) {
    return fetch(url, {
      cache: 'no-cache',
      method: 'DELETE',
      mode: 'cors',
      redirect: 'follow',
      referrer: 'no-referrer',
    })

  }

}

export default ShineHttpClient