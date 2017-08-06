let canv,
    c,
    x,
    y = x = 300,
    pw = 10,
    ph = 20,
    xv,
    yv = xv = 0,
    grav,
    defG = grav = 1,
    onG = false,
    hLeft,
    hRight,
    hDown = hRight = hLeft = false,
    plat = [],
    w, h,
    xs = 4;
ys = 10,
    fDown = false,
    colors = [],
    dead = false;
window.onload = () => {
    canv = document.getElementById("gc");
    w = canv.width;
    h = canv.height;
    c = canv.getContext("2d");
    setInterval(update, 1000/30);
    document.addEventListener("keydown", keyDown);
    document.addEventListener("keyup", keyUp);
    for (var i = 0; i < 30; i++) {
        plat[i] = {
            x: Math.random() * 750,
            y: Math.random() * 550,
            w: Math.random() * 100 + 50,
            h: Math.random() * 50 + 50,
            t: Math.floor(Math.random() * 3)
        }
    }
    colors[0] = "white";
    colors[1] = "lime";
    colors[2] = "red";
}

function update() {
    if(onG) {
        if(hDown) {
            ph = 10;
            if(fDown) {
                y += 10;
                fDown = false;
            }
        } else {
            ph = 20;
            if(fDown) {
                y -= 10;
                fDown = false;
            }
        }
        xv *= 0.8;
    } else {
        yv += grav;
    }
    if(yv > ys) {
        yv = ys;
    }
    if(!hDown) {
        if(hLeft != hRight && onG) {
            if(hLeft) {
                xv -= 1;
            }
            if(hRight) {
                xv += 1;
            }
        }
    }
    x += xv;
    y += yv;

    c.fillStyle = "black";
    c.fillRect(0, 0, w, h);

    onG = false;
    for (let p in plat) {
        if (plat.hasOwnProperty(p)) {
            let q = plat[p];

            if(x + pw > q.x && x < q.x + q.w) {
                if(y + ph >= q.y && y + ph <= q.y + yv) {
                    onG = true;
                    y = q.y - ph;
                    yv = 0;
                }
            }

            switch (q.t) {
                case 0:
                    if(x + pw > q.x && x < q.x + q.w) {
                        if(y > q.y + q.h + yv && y < q.y + q.h) {
                            yv = 0;
                            y = q.y + q.h;
                        }
                    }
                    if(y + ph >= q.y && y <= q.y + q.h) {
                        if(x + pw >= q.x && x + pw <= q.x + xv) {
                            xv = 0;
                            x = q.x - pw;
                        } else if(x >= q.x + q.w + xv && x <= q.x + q.w) {
                            xv = 0;
                            x = q.x + q.w;
                        }
                    }
                    break;
                case 1:
                    break;
                case 2:
                    if(x + pw > q.x && x < q.x + q.w) {
                        if(y + ph >= q.y && y + ph <= q.y + yv) {
                            ph = 0;
                            pw = 0;
                            dead = true;
                        } else if(y > q.y + q.h + yv && y < q.y + q.h) {
                            ph = 0;
                            pw = 0;
                            dead = true;
                        }
                    }
                    if(y + ph >= q.y && y <= q.y + q.h) {
                        if(x + pw >= q.x && x + pw <= q.x + xv) {
                            ph = 0;
                            pw = 0;
                            dead = true;
                        } else if(x >= q.x + q.w + xv && x <= q.x + q.w) {
                            ph = 0;
                            pw = 0;
                            dead = true;
                        }
                    }
                    break;
            }

            c.fillStyle = colors[q.t];
            c.fillRect(q.x, q.y, q.w, q.h);
        }
    }

    c.fillStyle = "blue";
    c.fillRect(x, y, pw, ph);
}

function keyDown(e) {
    switch (e.keyCode) {
        case 37:
            hLeft = true;
            break;
        case 38:
            if(onG) {
                yv = -ys;
            }
            grav = 0.45;
            break;
        case 39:
            hRight = true;
            break;
        case 40:
            if(!hDown) {
                hDown = true;
                fDown = true;
            }
            break;
    }
}

function keyUp(e) {
    switch (e.keyCode) {
        case 37:
            hLeft = false;
            break;
        case 38:
            grav = defG;
            break;
        case 39:
            hRight = false;
            break;
        case 40:
            hDown = false;
            fDown = true;
            break;
    }
}