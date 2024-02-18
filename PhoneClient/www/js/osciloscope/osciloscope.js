class Osciloscope {

  #ctx
  #width
  #height

  #offsetX
  #offsetY
  #offsetWidth
  #offsetHeight
  
  #middleX
  #middleY

  #borderColor
  #fillStyle
  
  #gridXPerSide
  #gridYPerSide
  #gridStepX
  #gridStepY

  constructor(ctx, width, height) {
    this.#ctx = ctx
    this.#width = width
    this.#height = height

    this.#offsetX = 10
    this.#offsetY = 10
    this.#offsetWidth = this.#width - 20
    this.#offsetHeight = this.#height - 10

    this.#middleX = this.#offsetX + this.#offsetWidth
    this.#middleY = this.#offsetY + this.#offsetHeight

    this.#borderColor = "#cacaca"
    this.#fillStyle = "#cacaca"

    this.#middleX = this.#offsetWidth / 2 + 10
    this.#middleY = this.#offsetHeight / 2 + 10

    this.#gridXPerSide = 2
    this.#gridYPerSide = 5

    this.#gridStepX = (this.#middleX - this.#offsetX) / this.#gridXPerSide
    this.#gridStepY = (this.#offsetY - this.#middleY) / this.#gridYPerSide

  }

  wacky_round(number, places) {
    var multiplier = Math.pow(10, places+2); // get two extra digits
    var fixed = Math.floor(number*multiplier); // convert to integer
    fixed += 44; // round down on anything less than x.xxx56
    fixed = Math.floor(fixed/100); // chop off last 2 digits
    return fixed/Math.pow(10, places);
  }


  redraw() {
    this.#ctx.clearRect(0, 0, window.innerWidth, window.innerHeight - 10)
    this.#drawFrame()
  }

  #drawFrame() {

    // Draw frame
    this.#ctx.strokeStyle = this.#borderColor
    this.#ctx.beginPath()
    this.#ctx.roundRect(this.#offsetX, this.#offsetY, this.#offsetWidth, this.#offsetHeight, [10])
    this.#ctx.stroke()

    // Draw middle Y
    this.#ctx.strokeStyle = '#da5103'
    this.#ctx.beginPath();
    this.#ctx.moveTo(this.#middleX, this.#offsetY)
    this.#ctx.lineTo(this.#middleX, this.#height)
    this.#ctx.stroke()

    this.#ctx.fillStyle = this.#fillStyle
    this.#ctx.font = "16px serif"
    this.#ctx.fillText(String(serialData.max_u_value), this.#middleX + 5, this.#offsetY + 15)

    this.#ctx.fillStyle = this.#fillStyle
    this.#ctx.font = "16px serif";
    this.#ctx.fillText(String(0), this.#middleX + 5, this.#middleY - 5)

    // Draw middle X
    this.#ctx.beginPath();
    this.#ctx.moveTo(this.#offsetX, this.#middleY)
    this.#ctx.lineTo(this.#offsetWidth + 10, this.#middleY)
    this.#ctx.stroke()

    this.#ctx.fillStyle = this.#fillStyle
    this.#ctx.font = "16px serif"
    this.#ctx.fillText(String(serialData.min_u_value), this.#middleX + 5, this.#height - 5)

    this.#ctx.strokeStyle = this.#borderColor
    let lineValue = serialData.max_u_value / this.#gridYPerSide
    for (let i = 1; i < this.#gridYPerSide; i++) {
      this.#ctx.beginPath();
      this.#ctx.moveTo(this.#offsetX, this.#middleY + i * this.#gridStepY)
      this.#ctx.lineTo(this.#offsetWidth + 10, this.#middleY  + i * this.#gridStepY)
      this.#ctx.stroke()
      this.#ctx.fillText(String(this.wacky_round(i * lineValue, 2)), this.#middleX + 5, this.#middleY + i * this.#gridStepY - 5)
    }
    lineValue = serialData.min_u_value / this.#gridYPerSide
    for (let i = 1; i < this.#gridYPerSide; i++) {
      this.#ctx.beginPath();
      this.#ctx.moveTo(this.#offsetX, this.#middleY - i * this.#gridStepY)
      this.#ctx.lineTo(this.#offsetWidth + 10, this.#middleY - i * this.#gridStepY)
      this.#ctx.stroke()
      this.#ctx.fillText(String(this.wacky_round(i * lineValue, 2)), this.#middleX + 5, this.#middleY - i * this.#gridStepY - 5)
    }

    for (let i = 1; i < this.#gridXPerSide; i++) {
      this.#ctx.beginPath();
      this.#ctx.moveTo(this.#middleX + i * this.#gridStepX, this.#offsetY)
      this.#ctx.lineTo(this.#middleX + i * this.#gridStepX, this.#height)
      this.#ctx.stroke()
      this.#ctx.beginPath();
      this.#ctx.moveTo(this.#middleX - i * this.#gridStepX, this.#offsetY)
      this.#ctx.lineTo(this.#middleX - i * this.#gridStepX, this.#height)
      this.#ctx.stroke()
    }

  }

}