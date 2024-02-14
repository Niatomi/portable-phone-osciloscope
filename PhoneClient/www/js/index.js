/*
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing,
 * software distributed under the License is distributed on an
 * "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY
 * KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations
 * under the License.
 */

// Wait for the deviceready event before using any of Cordova's device APIs.
// See https://cordova.apache.org/docs/en/latest/cordova/events/events.html#deviceready
document.addEventListener('deviceready', onDeviceReady, false);

let ctx

function onDeviceReady() {

    canvas = document.getElementById('canvas')
    canvas.width = window.innerWidth
    canvas.height = window.innerHeight - 10
    ctx = canvas.getContext("2d")

    const serialDataProxy = init_proxy_generator((target, key, value) => {
      target[key] = value
      return true
    })
  
    setInterval(function() {
      serialDataProxy.current_value = Math.round(Math.random() * (5 + 5) - 5)
      serialDataProxy.min_u_value = 5
      serialDataProxy.max_u_value = 5
    }, 100);

    window.addEventListener('touchmove', (event) => {
      console.log(event);
      let touches = event.changedTouches
      ctx.clearRect(0, 0, window.innerWidth, window.innerHeight - 10)
      for (let touch of touches) {
        ctx.fillStyle = "green"
        ctx.fillRect(touch.clientX - 50, touch.clientY - 50, 100, 100);
      }
    })
}
