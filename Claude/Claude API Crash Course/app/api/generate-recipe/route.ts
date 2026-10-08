import { NextResponse } from "next/server";

export async function POST(request: Request) {
  const { items, filters } = await request.json()

  return NextResponse.json({
    recipe: 'dummy recipe'
  })
}