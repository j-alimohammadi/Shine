class ShineHttpClient {
  static postData (url, data) {

    if (!data) {
      data = []
    }
    return fetch(url, {
      body: JSON.stringify(data),
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

  static putData (url, data) {
    if (!data) {
      data = []
    }
    return fetch(url, {
      body: JSON.stringify(data),
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