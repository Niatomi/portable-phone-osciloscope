const serialData = {
  current_value: null,
  max_u_value: null,
  min_u_value: null
}

function init_proxy_generator(func) {
    return new Proxy(serialData, {
      set: func
    });
}