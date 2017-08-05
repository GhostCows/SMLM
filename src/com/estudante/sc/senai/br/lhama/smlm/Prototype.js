var canv, c, x, y = x = 300, pw = 10, ph = 20, xv, yv = xv = 0, grav = .6, onG = !1, hLeft, hRight,
    hDown = hRight = hLeft = !1, plat = [], w, h, xs = 4;
ys = 10;
fDown = !1;
colors = [];
dead = !1;
window.onload = function () {
    canv = document.getElementById("gc");
    w = canv.width;
    h = canv.height;
    c = canv.getContext("2d");
    setInterval(update, 1E3 / 30);
    document.addEventListener("keydown", keyDown);
    document.addEventListener("keyup", keyUp);
    for (var b = 0; 30 > b; b++)plat[b] = {
        x: 750 * Math.random(),
        y: 550 * Math.random(),
        w: 100 * Math.random() + 50,
        h: 50 * Math.random() + 50,
        t: Math.floor(3 * Math.random())
    };
    colors[0] = "white";
    colors[1] = "lime";
    colors[2] = "red"
};
function update() {
    onG ? (hDown ? (ph = 10, fDown && (y += 10, fDown = !1)) : (ph = 20, fDown && (y -= 10, fDown = !1)), xv *= .8) : yv += grav;
    yv > ys && (yv = ys);
    !hDown && hLeft != hRight && onG && (hLeft && --xv, hRight && (xv += 1));
    x += xv;
    y += yv;
    c.fillStyle = "black";
    c.fillRect(0, 0, w, h);
    onG = !1;
    for (var b in plat)if (plat.hasOwnProperty(b)) {
        var a = plat[b];
        x + pw > a.x && x < a.x + a.w && y + ph >= a.y && y + ph <= a.y + yv && (onG = !0, y = a.y - ph, yv = 0);
        switch (a.t) {
            case 0:
                x + pw > a.x && x < a.x + a.w && y > a.y + a.h + yv && y < a.y + a.h && (yv = 0, y = a.y + a.h);
                y + ph >= a.y && y <= a.y + a.h && (x + pw >= a.x && x + pw <= a.x + xv ? (xv = 0, x = a.x - pw) : x >= a.x + a.w + xv && x <= a.x + a.w && (xv = 0, x = a.x + a.w));
                break;
            case 2:
                x + pw > a.x && x < a.x + a.w && (y + ph >= a.y && y + ph <= a.y + yv ? (pw = ph = 0, dead = !0) : y > a.y + a.h + yv && y < a.y + a.h && (pw = ph = 0, dead = !0)), y + ph >= a.y && y <= a.y + a.h && (x + pw >= a.x && x + pw <= a.x + xv ? (pw = ph = 0, dead = !0) : x >= a.x + a.w + xv && x <= a.x + a.w && (pw = ph = 0, dead = !0))
        }
        c.fillStyle = colors[a.t];
        c.fillRect(a.x, a.y, a.w, a.h)
    }
    c.fillStyle = "blue";
    c.fillRect(x, y, pw, ph)
}
function keyDown(b) {
    switch (b.keyCode) {
        case 37:
            hLeft = !0;
            break;
        case 38:
            onG && (yv = -ys);
            grav = .45;
            break;
        case 39:
            hRight = !0;
            break;
        case 40:
            hDown || (fDown = hDown = !0)
    }
}
function keyUp(b) {
    switch (b.keyCode) {
        case 37:
            hLeft = !1;
            break;
        case 38:
            grav = .6;
            break;
        case 39:
            hRight = !1;
            break;
        case 40:
            hDown = !1, fDown = !0
    }
};