import { z } from "zod"
import { zodOutputFormat } from "@anthropic-ai/sdk/helpers/zod"
import Anthropic from "@anthropic-ai/sdk"
import { NextResponse } from "next/server"

const client = new Anthropic({
  apiKey: process.env["ANTHROPIC_API_KEY"] || "",
})

const RecipeSchema = z.object({
  title: z.string(),
  description: z.string(),
  cookingTimeMinutes: z.number(),
  servings: z.number(),
  ingredients: z.array(z.object({
    name: z.string(),
    quantity: z.string(),
  })),
  steps: z.array(z.string()),
  notes: z.array(z.string()).optional(),
})

export async function POST(request: Request) {
  const { items = [], filters = [] } = await request.json()

  const message = await client.messages.parse({
    output_config: {
      format: zodOutputFormat(RecipeSchema),
    },
    model: "claude-haiku-4-5",
    max_tokens: 1024,
    messages: [
      {
        role: "user",
        content: `
          // IMPORTANT: Respond in Polish language.
          Create a recipe from only these ingredients: ${items.join(", ")}.
          Apply these constraints to the recipe: ${filters.join(", ") || 'no additional constraints.'}
          IMPORTANT: Try to be creative and don't always go for the obvious recipe.
          IMPORTANT: Before making the recipe, think of about 5 dishes that can be made from the provided
          ingredients and respond with only one of them at random. Do NOT tell me about the other options.
        `
      },
    ],
  })

  console.log(message.parsed_output)

  return NextResponse.json({
    recipe: message.parsed_output,
  })
}
