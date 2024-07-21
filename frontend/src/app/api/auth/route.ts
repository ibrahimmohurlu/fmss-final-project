import { error } from "console";
import { cookies } from "next/headers";
import { NextRequest, NextResponse } from "next/server";

export async function POST(req: NextRequest) {
    const { email, password } = await req.json();
    if (!email) {
        return NextResponse.json({ status: "error", message: "email required" }, { status: 400 })
    }
    if (!password) {
        return NextResponse.json({ status: "error", message: "password required" }, { status: 400 })
    }
    const authHeader = `Basic ${new Buffer(email + ':' + password).toString('base64')}`;
    const response = await fetch(`http://localhost:8080/api/v1/users/${email}`, {
        method: "GET",
        headers: {
            "Authorization": authHeader
        }
    });
    if (!response.ok) {
        return NextResponse.json({ status: "error", message: "Invalid Credentials" }, { status: 400 });
    }
    const user = await response.json() as ServerLoginResponse;
    if (email !== user.email && password !== user.password) {
        return NextResponse.json({ status: "error", message: "Invalid Credentials" }, { status: 400 });
    }
    cookies().set('auth', authHeader)
    return NextResponse.json({ status: "success", message: "Logged in successfully" }, { status: 200 })
}